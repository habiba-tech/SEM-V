
def encrypt(plain):
    even = ""
    odd = ""

    for i in range(len(plain)):
        if i % 2 == 0:
            even += plain[i]
        else:
            odd += plain[i]

    return even + odd


def decrypt(cipher):
    mid = (len(cipher) + 1) // 2
    even = cipher[:mid]
    odd = cipher[mid:]
    plain = ""

    for i in range(len(odd)):
        plain += even[i]
        plain += odd[i]

    if len(even) > len(odd):
        plain += even[-1]

    return plain


plain = input("Enter A Plain Text : ").strip().lower()
cipher = encrypt(plain)

print(f"Cipher Text : {cipher}")
print(f"Plain Text : {decrypt(cipher)}")
