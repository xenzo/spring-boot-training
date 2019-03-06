package com.example.demo

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.SelectionKey.OP_READ
import java.nio.channels.SelectionKey.OP_WRITE
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel

@RunWith(SpringRunner::class)
@SpringBootTest
class DemoApplicationTests {

    @Test
    fun contextLoads() {

        val selector = Selector.open()
        val server = ServerSocketChannel.open()

        server.bind(InetSocketAddress(8878))
        server.configureBlocking(false)
        val ssk = server.register(selector, server.validOps())
        ssk.attach(Acceptor(selector, server))
        Thread {
            while (true) {
                selector.select()
                val selectedKeys = selector.selectedKeys()
                selectedKeys.iterator().forEach { ops ->
                    when {
                        ops.isAcceptable || ops.isWritable || ops.isReadable ->
                            (ops.attachment() as Runnable).run()
                    }
                }
                selectedKeys.clear()
            }
        }.run()
    }

    class Handler(private val selector: Selector, private val channel: SocketChannel) : Runnable {
        var selectedKey: SelectionKey? = null
        val buff = ByteBuffer.allocateDirect(4096)

        fun init() {
            channel.configureBlocking(false)
            selectedKey = channel.register(selector, 0)
            selectedKey?.attach(this)
            selectedKey?.interestOps(OP_READ)
            selector.wakeup()
        }

        override fun run() {
            when {
                selectedKey?.isReadable ?: false -> read()
                selectedKey?.isWritable ?: false -> write()
            }
        }

        private fun write() {
            selectedKey?.interestOps(OP_READ)
        }

        private fun read() {
            val cnt = channel.read(buff)
            channel
            if (cnt > 0) {
                buff.flip()

            }
            selectedKey?.interestOps(OP_WRITE)
        }
    }

    class Acceptor(private val selector: Selector, private val server: ServerSocketChannel) : Runnable {
        override fun run() {
            val channel = server.accept()
            Handler(selector, channel).init()
        }
    }
}

