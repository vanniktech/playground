package com.vanniktech.playground.kmp

import kotlin.test.Test
import kotlin.test.assertEquals

class SomeTest {
  @Test fun foo() {
    val string = """
    |<audio class="wp-audio-shortcode" id="audio-40030-2" preload="none" style="width: 100%;" controls="controls"><source type="audio/mpeg" src="https://pinecast.com/listen/da6c4f61-4c76-486f-888b-f200cdcc3903.mp3?_=2" /><a href="https://pinecast.com/listen/da6c4f61-4c76-486f-888b-f200cdcc3903.mp3">https://pinecast.com/listen/da6c4f61-4c76-486f-888b-f200cdcc3903.mp3</a></audio>
    |    <p>Kia ora Mosen At Largers. A reminder that this podcast is indexed by chapter. If<br />
    |    you listen with a podcast client that offers chapter support, you can easily<br />
    |    skip between segments. On the show this week:</p>
    |    <p>Introduction,0:00.000</p>
    |    <p><a href="https://www.buzzfeednews.com/article/emersonmalone/ableism-language-disability" rel="nofollow">Great Buzzfeed article on ableist<br />
    |    language</a>,1:38.872</p>
    |    <p>The Blind man and the dell. And my new Lenovo ThinkPad X1 Carbon,2:46.456</p>
    |    <p>Scribe for Personal Documents feedback,27:03.234</p>
    |    <p>More thoughts on meeting presenters describing themselves,29:16.007</p>
    |    <p>Our Chromebook demo continues with a look at Braille support,59:56.124</p>
    |    <p>Some thoughts onChromebooks,1:28:12.780</p>
    |    <p>The Bonnie Bulletin,1:46:16.444</p>
    |    <p>Should Apple police third-party accessibility?,1:54:35.283</p>
    |    <p>Android thoughts,1:57:19.341</p>
    |    <p>Closing and contact info,1:59:30.271</p>
    |    <p>Share your thoughts on these topics or any others. Drop me an email in writing<br />
    |    or with an audio attachment, Jonathan at <a href="http://MushroomFm.com" rel="nofollow">MushroomFm.com</a>, or phone the listener<br />
    |    line in the United States, +1864-60Mosen, that&#8217;s +18646066736.</p>
    |    <p>Keep up with Mosen At Large between episodes. <a href="http://twitter.com/mosenatlarge" rel="nofollow">Follow MosenAtLarge on<br />
    |    Twitter</a> where you&#8217;ll get audio extras, links<br />
    |    to interesting news stories, sneak peeks about what&#8217;s coming up and more. If<br />
    |    you&#8217;d like to subscribe to our announcements only email list, please send email<br />
    |    to &lt;media-subscribe@mosen.org&gt;</p>
    |    <p>And if you like the show, we&#8217;d love a positive review and for you to spread the<br />
    |    word. Thank you.</p>
      """.trimMargin()

    val regex = Regex("<p>.*,\\d")

    assertEquals(
      expected = listOf(
        "<p>Introduction,0",
        "<p>The Blind man and the dell. And my new Lenovo ThinkPad X1 Carbon,2",
        "<p>Scribe for Personal Documents feedback,2",
        "<p>More thoughts on meeting presenters describing themselves,2",
        "<p>Our Chromebook demo continues with a look at Braille support,5",
        "<p>Some thoughts onChromebooks,1",
        "<p>The Bonnie Bulletin,1",
        "<p>Should Apple police third-party accessibility?,1",
        "<p>Android thoughts,1",
        "<p>Closing and contact info,1",
      ),
      actual = regex.findAll(string).map { match ->
        match.value
      }.toList()
    )
  }
}
