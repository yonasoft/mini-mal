package com.yonasoft.minimal.network

import javax.inject.Singleton

//File to provide MAL authentication information utilities
@Singleton
class MALAuth {
        val responseType = "code"
        val clientId = "2ebd5b69560a6724b07682d4f5400fe9"
        val codeChallenge = "rW8fl1Plxx43l_f85VQq4JSkTlMHb6kVYMTUwTq6LJg2DtqhE6sQwjO6BbEHX0EXfc66Kk04qCb70UC4yWeamNB9GLhEHORY64SKuqcVZs2-ArWkG1J-8S3aHtrcfp3W"
        val state = "Requestmmal1029"
        val codeChallengeMethod = "plain"
        val grantType = "authorization_code"
        val redirectUri = "myapp://auth"
        //Authentication Uri
        val authUri = "https://myanimelist.net/v1/oauth2/"

        //Token Uri
        val tokenUri = "https://myanimelist.net/v1/oauth2/"

}