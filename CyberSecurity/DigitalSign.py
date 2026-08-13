# Import RSA Functionality for generating RSA public and private keys
from cryptography.hazmat.primitives.asymmetric import rsa
# Import padding required for secure 
from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.primitives import hashes

# Generate an RSA private key
private_key = rsa.generate_private_key(
   public_exponent=65537,
   key_size=2048
)

# Obtain the public key corresponding to the private key
public_key = private_key.public_key()

# Define the original message that will be digitally signed
message = b"Cyber Security Practical"

# Generate the digital signature using the private key
signature = private_key.sign(
   message,

   # Use RSA-PSS padding for secure RSA Signature
   padding.PSS(

      # Use MGF1 with SHA-256
      mgf=padding.MGF1(hashes.SHA256()),

      # Use a salt having the same length as SHA-256 output 
      salt_length=padding.PSS.MAX_LENGTH
   ),

   # USe SHA-256 as the hashing algorithm
   hashes.SHA256()
)

# Display the original message
print("Original Mesage: ")
print(message.decode())

# Display the generated digital signature 
print("\nDigital Signature: ")
print(signature.hex())

#Verify the digital signature against the original message
try:
   public_key.verify(
      signature,
      message,

   # Use the same RSA-PSS padding during verification
      padding.PSS(
         mgf=padding.MGF1(hashes.SHA256()),
         salt_length=padding.PSS.MAX_LENGTH
   ),

   # USE the same SHA-256 algorithm
      hashes.SHA256()
)

# This message is displayed when verification succeeds
   print("\nSignature Verification: SUCCESS")

   # Inform the user that the message is authentic
   print("The message is authentic and has not been modified.")

except Exception:
   # This block execute if signature verification fails
   print("\nSignature Verification: FAILED")

   # Inform the user that the message may have been modified
   print("The message is not authentic or has been modified.")

# Create a modified version of the original message
modified_message = b'Cyber Security Practical - Modified'

# Display the modified message 
print("\nModified Message: ")
print(modified_message.decode())

# Try to verify the old signature against the modified message
try:

   # Verify the signature using the modified message
   public_key.verify(
      signature,
      modified_message,

      # Use the same RSA-PSS padding
      padding.PSS
      (
         mgf=padding.MGF1(hashes.SHA256()),
         salt_length=padding.PSS.MAX_LENGTH
      ),

      # USe SHA-256 again
      hashes.SHA256()
   )

   # This should normally not execute because the message was changed
   print("\nModified Message Verification: SUCCESS")

except Exception:

   # Verification fails because the message is different
   print("\nModified Message Verification: FAILED")

   # Explain why the verification failed
   print("The message has been modified, so the signature is invalid")
