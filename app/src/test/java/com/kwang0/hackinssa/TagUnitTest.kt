package com.kwang0.hackinssa

import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.helper.ValidHelper
import org.junit.Assert
import org.junit.Test

class TagUnitTest {
    @Test
    fun tag_letters_lower_isValid() {
        Assert.assertEquals(true, ValidHelper.isTagValid("abcdefghijklmnopqrstuvwxyz"))
    }
    @Test
    fun tag_letters_isValid() {
        Assert.assertEquals(true, ValidHelper.isTagValid("ABCDEFGHIJKLMNOPQRSTUVWXYZ"))
    }
    @Test
    fun tag_numbers_isValid() {
        Assert.assertEquals(true, ValidHelper.isTagValid("0123456789"))
    }
    @Test
    fun tag_underscore_isValid() {
        Assert.assertEquals(true, ValidHelper.isTagValid("_________"))
    }
    @Test
    fun tag_letters_numbers_isValid() {
        Assert.assertEquals(true, ValidHelper.isTagValid("189fhWOEOW4hf89"))
    }
    @Test
    fun tag_letters_underscore_isValid() {
        Assert.assertEquals(true, ValidHelper.isTagValid("woea_wueAWOe__wuifaew"))
    }
    @Test
    fun tag_numbers_underscore_isValid() {
        Assert.assertEquals(true, ValidHelper.isTagValid("1290_1291299_1919"))
    }
    @Test
    fun tag_letters_numberss_underscore_isValid() {
        Assert.assertEquals(true, ValidHelper.isTagValid("wofOWNV3u210_hhG4__311h"))
    }
    @Test
    fun tag_dot_isValid() {
        Assert.assertEquals(true, ValidHelper.isTagValid("/au34up9rba0-2l.pz_+!@"))
    }
    @Test
    fun tags_isValid() {
        val tags = listOf(Tag("1", "11",1),
                Tag("2", "22",1))
        Assert.assertEquals(true, ValidHelper.isTagsValid(tags))
    }
    @Test
    fun tags_same_isValid() {
        val tags = listOf(Tag("1", "11",1),
                Tag("2", "22",1),
                Tag("3", "33",1),
                Tag("1", "11",1))
        Assert.assertEquals(true, ValidHelper.isTagsValid(tags))
    }
    @Test
    fun tags_size_isValid() {
        val tags = listOf(Tag("1", "11",1),
                Tag("2", "22",1),
                Tag("3", "33",1),
                Tag("4", "44",1),
                Tag("5", "55",1),
                Tag("6", "66",1))
        Assert.assertEquals(true, ValidHelper.isTagsValid(tags))
    }
    @Test
    fun tags_match_isValid() {
        val tags = listOf(Tag("1", "1f1f1f-",1),
                Tag("2", "22",1))
        Assert.assertEquals(true, ValidHelper.isTagsValid(tags))
    }
}