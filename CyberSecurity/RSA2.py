from math import gcd

p = int(input("Enter A Prime Number: "))
q = int(input("Enter Another Prime Number: "))

n = p*q
phi = (p-1)*(q-1)

e = 2
while gcd(e,phi) != 1:
 e += 1
print("Public Key :",(e,n))

d = 2
while (d*e) % phi != 1:
  d += 1
print("Private Key :", (d,n))

message = int(input("Enter A Message : "))

cipher = pow(message,e,n)
print("Encrypted Message : ", cipher)

message = pow(cipher,d,n)
print("Decrypted Message :", message)
