import hmac
import hashlib

def mac(msg, key):
    return hmac.new(key.encode(), msg.encode(), hashlib.sha256).hexdigest()

msg = input("Enter Message: ")
key = input("Enter Key: ")

code = mac(msg, key)
print("Generated MAC:", code)

rmsg = input("Received Message: ")
rkey = input("Received Key: ")
rmac = input("Received MAC: ")

if hmac.compare_digest(mac(rmsg, rkey), rmac):
    print("Message Verified")
else:
    print("Verification Failed")

modified = input("Modified Message: ")

if hmac.compare_digest(code, mac(modified, key)):
    print("No Tampering")
else:
    print("Tampering Detected")
