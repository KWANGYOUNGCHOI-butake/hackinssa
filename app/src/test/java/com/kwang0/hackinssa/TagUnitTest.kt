package com.kwang0.hackinssa

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
}