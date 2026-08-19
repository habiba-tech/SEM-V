# Diffie-Hellman Key Exchange 

p = int(input("Enter prime number (p): "))
g = int(input("Enter primitive root (g)"))

a = int(input("Enter Alice's private key : "))
b = int(input("Enter Bob's private key : "))

#public keys
A = pow(g,a,p)
B = pow(g,b,p)

#Shared Secret Keys
KA = pow(B, a, p)
KB = pow(A, b, p)

print("\nAlice's Public key:", A)
print("BOb's public key:",B)

print("\nAlice's shared Secret :", KA)
print("BOb's shared Secret :",KB)

if KA == KB :
    print("\nKey Exchange Sucessful !")
else:
    print("\n key Exchange Failed !")
