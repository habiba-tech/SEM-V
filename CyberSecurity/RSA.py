from math import gcd

# Function to find modular inverse
def mod_inverse(e,phi):
   for d in range(1,phi):
      if (d * e)%phi == 1:
         return d 
   return None

# Input Prime number
p = int(input("Enter First Prime Number (p): "))
q = int(input("Enter Second Prime Number (q): "))

# Step 1
n = p * q

print("The value of n is :",n)
# Step 2
phi = (p - 1) * (q - 1)

# Step 3
e = int(input("Enter Value of e: "))

while gcd(e,phi) != 1:
   print("e must be coprime with", phi)
   e = int(input("Enter another e: "))

# Step 4
d = mod_inverse(e,phi)
print("\nPublic Key: ",(e,n))
print("Private key: ",(d,n))

# Encryption
message = int(input("\nEnter your messagge (number less than n): "))
cipher = pow(message , e ,n)
print("Encrypted Message: ",cipher)

# Decryption
plain = pow(cipher,d,n)
print("Decrypted Message: ", plain)
