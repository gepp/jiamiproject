package com.jdk2010.framework.util;

public class RSATest {

    static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOGgKYph3u/AgvZzSP2mQjjHuMT8mPKv3vw8apI5FfDPmC/X9pSuhFGU5LLom2A5jNdT7L9y9UiqHQD8vBnCPpSNQgDsAlv6lrrVgknquLuu0svAADOZCOT4q9ZbTbY6ST4TjNpqBMD+Agun/uufq0g3UYt1alF70N52marAnEmwIDAQAB";
    static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI4aApimHe78CC9nNI/aZCOMe4xPyY8q/e/DxqkjkV8M+YL9f2lK6EUZTksuibYDmM11Psv3L1SKodAPy8GcI+lI1CAOwCW/qWutWCSeq4u67Sy8AAM5kI5Pir1ltNtjpJPhOM2moEwP4CC6f+65+rSDdRi3VqUXvQ3naZqsCcSbAgMBAAECgYBquoButH3RDHz9VY+15/rEdHQrC5ZAsLKvjaS9TtiVwoH473rt03Qmyf3zG84U5hizYDc9LLfg0Ir+YY7RKPwSxhLvNRvSpCP+zKgRe5xwWEHz0pR6aNB1hJb7eMpWhvKDbPazDiGt/09QrtBEGifgvbAnOuQBRZwpSqYGo/AGkQJBAP15kAx+ptiHwTYKdLwqkHhZb7bGR3K+DGsH6sNPszRwr73W3iPId7Z9RkzkOO/ci79rL51lsEH3H8z+X02M1jkCQQCPhGl9xnISl2mrunoizqPvRiWbLIyqhSYOZ/8jkvl8F2eCKrfnoZQ9G4930qrpk+Kz3AjXeo28aWXJFOLp6dFzAkBHqvFhtwWxv2CPyREMivRiMtd1VEarWLTmJkzogEZpE1t9T51rfKjsfis8XmMC3tbcfo/t7uyRGjBxo5KKuMFxAkAZs0SQ2XCkE6lAr2wfv5Frz8v8NcYa4y0ld0ohPLigmDBZBv1X5X9+UeknM/qXSXxXaRfMPo4TJ2yqzaq06MIxAkEAxmdxde9BVZkDeVi0lcjgXo7GAobbyGaxrCyXeUkZvQmRsoXQxP69QoLdXtmRa/pYru68sHSGycyDVfe/FNOvoA==";

    // static void init() {
    // try {
    // Map<String, Object> keyMap = RSAUtil.genKeyPair();
    // publicKey = RSAUtil.getPublicKey(keyMap);
    // privateKey = RSAUtil.getPrivateKey(keyMap);
    // System.out.println("公钥: \n\r" + publicKey);
    // System.out.println("私钥： \n\r" + privateKey);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    public static void main(String[] args) throws Exception {
        // init();
        testRSA();
        // testSign();
    }

    static void testRSA() throws Exception {
        String source = "铜陵地税~2019-12-01";
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtil.encryptByPublicKey(data, publicKey);
        String jmh = Base64Util.encode(encodedData);
        System.err.println("将以下文字复制进license.key中......");
        System.out.println("key=" + privateKey);
        System.out.println("value=" + jmh);
        // encodedData = Base64Util.decode(jmh);
        // byte[] decodedData = RSAUtil.decryptByPrivateKey(encodedData, privateKey);
        // String target = new String(decodedData);
        // System.out.println("解密后文字: \r\n" + target);

    }

    static void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String source = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtil.encryptByPrivateKey(data, privateKey);
        System.out.println("加密后：\r\n" + Base64Util.encode(encodedData));
        byte[] decodedData = RSAUtil.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        System.out.println("解密后: \r\n" + target);
        System.err.println("私钥签名——公钥验证签名");
        String sign = RSAUtil.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = RSAUtil.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\r" + status);
    }

}
