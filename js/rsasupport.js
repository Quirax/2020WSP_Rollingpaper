function RSA(modulus, exp) {
    this.rsa = new RSAKey();
    this.rsa.setPublic(modulus, exp);

    this.encrypt = function(plain) { return this.rsa.encrypt(plain); };
}