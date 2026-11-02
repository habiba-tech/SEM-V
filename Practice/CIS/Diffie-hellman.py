p = int(input("Enter a prime Number (p) :"))
g = int(input("Enter a primitive root (g) :"))

a = int(input("Enter a Alice's Private Key : "))
b = int(input("Enter aBob's Private Key : "))

A = pow(g,a,p)
B = pow(g,b,p)

KA = pow(B,a,p)
KB = pow(A,b,p)

print("Alice's Public key :", A)
print("Bob's public key :", B)

print("Alice's Shared Sceret : ",KA)
print("Bob's Shared Sceret : ",KB)

if KA == KB :
    print("Key Exchanged successfully")
else : 
    print("Key Exchanged Failed")
