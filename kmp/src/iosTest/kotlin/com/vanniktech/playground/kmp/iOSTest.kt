package com.vanniktech.playground.kmp

import kotlin.test.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class iOSTest {
  @Test fun foo() {
    runBlocking {
      async(Dispatchers.Default) {
        findImages("""<p style="text-align: center;">&nbsp;Nous avons retrouvé notre envoyé spécial Claude P - tout bronzé - qui tournait autour de l'Arc de Triomphe pour vérifier si les grimpeurs sauvages n'avaient pas endommagé "son" emballage !</p><p style="text-align: center;"><br /></p><div class="separator" style="clear: both; text-align: center;"><a href="https://blogger.googleusercontent.com/img/a/AVvXsEja70eP9GggtU4nXJQ3fpV1tMLX4qbjexq2aHFYZwuRnmBsibW2nd9oGdNaeco7Xcm1sOBd0vSn7jFagC6Z1sa_HI-B-GjH0Dh-mxm6aIia9014RXl67Tn8eJ7kHxDxgc-6kdTpSFy7hLMy9yasDeznBkFVgZJzJXSMkrxjpJ5wJDfbp39gBej0XYIF4Q=s1024" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" data-original-height="768" data-original-width="1024" height="240" src="https://blogger.googleusercontent.com/img/a/AVvXsEja70eP9GggtU4nXJQ3fpV1tMLX4qbjexq2aHFYZwuRnmBsibW2nd9oGdNaeco7Xcm1sOBd0vSn7jFagC6Z1sa_HI-B-GjH0Dh-mxm6aIia9014RXl67Tn8eJ7kHxDxgc-6kdTpSFy7hLMy9yasDeznBkFVgZJzJXSMkrxjpJ5wJDfbp39gBej0XYIF4Q=s320" width="320" /></a></div><br /><div class="separator" style="clear: both; text-align: center;"><a href="https://blogger.googleusercontent.com/img/a/AVvXsEiGM3VcwS0WKeV5s2Y-8EPVKU5nL-YPkM1K8rvhjamAusQgmBJq9dencSUAmd0pQh8SZKh2ttw6WLf9O3R93MfpP-f00ACRsdNr0pXBI1b8Bo3U-ZMms0-7jwey-EJan04yV4kisiQEVPtJImaPMH2GCSmjS6bUi8m9B9xMg1xACQwSb1hslw8ggULqSQ=s768" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" data-original-height="768" data-original-width="576" height="320" src="https://blogger.googleusercontent.com/img/a/AVvXsEiGM3VcwS0WKeV5s2Y-8EPVKU5nL-YPkM1K8rvhjamAusQgmBJq9dencSUAmd0pQh8SZKh2ttw6WLf9O3R93MfpP-f00ACRsdNr0pXBI1b8Bo3U-ZMms0-7jwey-EJan04yV4kisiQEVPtJImaPMH2GCSmjS6bUi8m9B9xMg1xACQwSb1hslw8ggULqSQ=s320" width="240" /></a></div><br /><div class="separator" style="clear: both; text-align: center;"><a href="https://blogger.googleusercontent.com/img/a/AVvXsEgYfg0TXyi2pgfi8PNCOtmkSuiQFYFApHeHLRbESG8sFdgSmuuL_MbUvh54K2KplhhZpaPHPrRrIso6OwbVPG59QSaOQ3ygbugmpPfO2w1ZRrpQYSt_K5dLlralRShmwWy4BcxIPlmPJ0tQbnoF5h3riVCn0GcSqSG3TkO8LPwKODtuqhJia1bDFeULvA=s1024" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" data-original-height="768" data-original-width="1024" height="240" src="https://blogger.googleusercontent.com/img/a/AVvXsEgYfg0TXyi2pgfi8PNCOtmkSuiQFYFApHeHLRbESG8sFdgSmuuL_MbUvh54K2KplhhZpaPHPrRrIso6OwbVPG59QSaOQ3ygbugmpPfO2w1ZRrpQYSt_K5dLlralRShmwWy4BcxIPlmPJ0tQbnoF5h3riVCn0GcSqSG3TkO8LPwKODtuqhJia1bDFeULvA=s320" width="320" /></a></div><br /><div class="separator" style="clear: both; text-align: center;"><a href="https://blogger.googleusercontent.com/img/a/AVvXsEhkYwEwDVXHDSHlBvlCSWgYW8MvbRv0bf-yUJ8tm6cTPa1mDsEP3uMbITsufDNhkib5AqRa5p6v3aa4lbQX1jxCEr0tGJRQ8oI2lJknfKsHIpq4m8Pd772wIz-5I3DGydiaoASQtTP-j1tJy1U9-UpjHoNuWWln_99H_j1DbFaipjOtZGWhjq25917tiw=s768" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" data-original-height="768" data-original-width="576" height="320" src="https://blogger.googleusercontent.com/img/a/AVvXsEhkYwEwDVXHDSHlBvlCSWgYW8MvbRv0bf-yUJ8tm6cTPa1mDsEP3uMbITsufDNhkib5AqRa5p6v3aa4lbQX1jxCEr0tGJRQ8oI2lJknfKsHIpq4m8Pd772wIz-5I3DGydiaoASQtTP-j1tJy1U9-UpjHoNuWWln_99H_j1DbFaipjOtZGWhjq25917tiw=s320" width="240" /></a></div><br /><div class="separator" style="clear: both; text-align: center;"><a href="https://blogger.googleusercontent.com/img/a/AVvXsEgWuvo5eFaAChKqJl9eT05sLYvnBjexbHpqSCdE-pKoZ7uwQ7BhRaVB2sLqwGFd0hmizdO4C1bvSXBVaDqFpbLyX2g4ppDneU2BE-0ES75KXMBXAcArk9Cwbtkz4HS7nvqmkmFyDW-xSHwXkUsUmR3ZG4tn6UXivgKZUuuSRYSoT2qsbepUisTPAg_KuA=s1024" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" data-original-height="768" data-original-width="1024" height="240" src="https://blogger.googleusercontent.com/img/a/AVvXsEgWuvo5eFaAChKqJl9eT05sLYvnBjexbHpqSCdE-pKoZ7uwQ7BhRaVB2sLqwGFd0hmizdO4C1bvSXBVaDqFpbLyX2g4ppDneU2BE-0ES75KXMBXAcArk9Cwbtkz4HS7nvqmkmFyDW-xSHwXkUsUmR3ZG4tn6UXivgKZUuuSRYSoT2qsbepUisTPAg_KuA=s320" width="320" /></a></div><br /><div class="separator" style="clear: both; text-align: center;"><a href="https://blogger.googleusercontent.com/img/a/AVvXsEihKnVUwghM_1P803gjU1anv_zdMrCRhfinBx4fZGvjMFIaQAMziPEPTO_SLvqCaLDBxckc4427sWQrkObpmhDBXaVuL3n8cjbOuik6XdxZRZ6CO3lJkYgeFZDNLtJvCQ_SvsK3IEUpl1ads8UzFgx5o_egB9clx_ljCuFtQMa0IaFRIaP1yviSRnu_WA=s768" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" data-original-height="768" data-original-width="576" height="320" src="https://blogger.googleusercontent.com/img/a/AVvXsEihKnVUwghM_1P803gjU1anv_zdMrCRhfinBx4fZGvjMFIaQAMziPEPTO_SLvqCaLDBxckc4427sWQrkObpmhDBXaVuL3n8cjbOuik6XdxZRZ6CO3lJkYgeFZDNLtJvCQ_SvsK3IEUpl1ads8UzFgx5o_egB9clx_ljCuFtQMa0IaFRIaP1yviSRnu_WA=s320" width="240" /></a></div><br /><div class="separator" style="clear: both; text-align: center;"><a href="https://blogger.googleusercontent.com/img/a/AVvXsEgOJGcRkPC68XJY0nbqNaopkOyREMPOkLZ56yymj_A4T5U11QrgP2AxJltXbYGYwmVVquY9a2zlIaDGLmP1gpi09EyQqHCWfHybb7bAtj1wq793Q3qe2DYNuXh_c4hzFSy5886IsbiJxKC8hmLbGB-TlnPT3ZEIEDfBcaSme7dKk7LkLFWQlczKiylh-g=s1024" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" data-original-height="768" data-original-width="1024" height="240" src="https://blogger.googleusercontent.com/img/a/AVvXsEgOJGcRkPC68XJY0nbqNaopkOyREMPOkLZ56yymj_A4T5U11QrgP2AxJltXbYGYwmVVquY9a2zlIaDGLmP1gpi09EyQqHCWfHybb7bAtj1wq793Q3qe2DYNuXh_c4hzFSy5886IsbiJxKC8hmLbGB-TlnPT3ZEIEDfBcaSme7dKk7LkLFWQlczKiylh-g=s320" width="320" /></a></div><br /><div class="separator" style="clear: both; text-align: center;"><a href="https://blogger.googleusercontent.com/img/a/AVvXsEjbzLjSYPobvgqJOVDocEbMzsnFEXr1PatX1Pd4Fh8odPf_ZF3TYWzXVovXo4XkrMKIYFTeQque-AUrulknbFTdwUniRv-hFB1NTw6qmio3DiMlfm0QlTEtdbLOQjVcYbutIZXt_K2EXMJox73mS-84-lmwu3RneI5Wl1cSh26xyMZBd2on1bChvbvHpA=s1024" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" data-original-height="768" data-original-width="1024" height="240" src="https://blogger.googleusercontent.com/img/a/AVvXsEjbzLjSYPobvgqJOVDocEbMzsnFEXr1PatX1Pd4Fh8odPf_ZF3TYWzXVovXo4XkrMKIYFTeQque-AUrulknbFTdwUniRv-hFB1NTw6qmio3DiMlfm0QlTEtdbLOQjVcYbutIZXt_K2EXMJox73mS-84-lmwu3RneI5Wl1cSh26xyMZBd2on1bChvbvHpA=s320" width="320" /></a></div><br /><div class="separator" style="clear: both; text-align: center;"><a href="https://blogger.googleusercontent.com/img/a/AVvXsEi2zkg_MjTrWUypmVDeEIlEQcAbl20Yu4w0Pe0TCtDZCt3AHoMdiv9-WSWc_kua54KZSNB-9jlBySIMA78OOuvNnU8xCTvTIkKFYy31YFZK3cZcg19e3h4ns3_1imRz9k7NrylR1O5CxajhCqMYlfpoCBVQY68L11PWcQyzg59pbrnqKqkOvToOUT3mwg=s1024" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" data-original-height="768" data-original-width="1024" height="240" src="https://blogger.googleusercontent.com/img/a/AVvXsEi2zkg_MjTrWUypmVDeEIlEQcAbl20Yu4w0Pe0TCtDZCt3AHoMdiv9-WSWc_kua54KZSNB-9jlBySIMA78OOuvNnU8xCTvTIkKFYy31YFZK3cZcg19e3h4ns3_1imRz9k7NrylR1O5CxajhCqMYlfpoCBVQY68L11PWcQyzg59pbrnqKqkOvToOUT3mwg=s320" width="320" /></a></div><p style="text-align: center;">Je sais qu'il y en a qui n'aiment pas; j'avoue qu'après avoir été d'abord dubitatif, je reconnais que c'est une totale réussite !</p><div class="separator" style="clear: both; text-align: center;"><a href="https://blogger.googleusercontent.com/img/a/AVvXsEjiilYuvBf0oq359JX9M2Xa9uvE5JVfwPN-BY2y34HaQQPwZ7r4bCO9jxL2MppHweU_o7Mup3d8QHVYnnqBeENYq8FiX5r3KAWUWICFWpgaGmRFYVZ7Xd6WPVWXSckaYc5F7Th6oriUlYrZBBfeVvbHQYcO4myo1rRBgM_ShlfcuCuqtncEB-nPpwRSjQ=s1024" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" data-original-height="768" data-original-width="1024" height="240" src="https://blogger.googleusercontent.com/img/a/AVvXsEjiilYuvBf0oq359JX9M2Xa9uvE5JVfwPN-BY2y34HaQQPwZ7r4bCO9jxL2MppHweU_o7Mup3d8QHVYnnqBeENYq8FiX5r3KAWUWICFWpgaGmRFYVZ7Xd6WPVWXSckaYc5F7Th6oriUlYrZBBfeVvbHQYcO4myo1rRBgM_ShlfcuCuqtncEB-nPpwRSjQ=s320" width="320" /></a></div><br /><p></p><p style="text-align: center;">Merci à Claude pour ces images, merci à Christo pour cet emballage !</p><p style="text-align: center;">***</p><p style="text-align: center;"><br /></p>""")
      }.await()
    }
  }
}

private val REGEX_IMAGE = Regex("<img[\\w='\":/.,{}&; -]* src=\\s*(['\"])((?:(?!\\1|\\\\).|\\\\.)*)\\1?.*>")

internal fun findImages(description: String): List<MatchResult> {
  return REGEX_IMAGE.findAll(description)
    .toList() // This causes a segmentation fault when used in conjunction with coroutines.
}
