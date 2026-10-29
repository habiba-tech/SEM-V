import tensorflow as tf
from keras.applications import MobileNetV2
from keras.applications.mobilenet_v2 import preprocess_input
from keras.applications.imagenet_utils import decode_predictions

model = MobileNetV2(weights="imagenet")

path = input("Enter image path: ")

img = tf.image.decode_image(tf.io.read_file(path), channels=3)
img = tf.image.resize(img, (224, 224))
img = preprocess_input(tf.expand_dims(img, 0))

pred = model.predict(img, verbose=0)
idx = pred.argmax()
name = decode_predictions(pred, top=1)[0][0][1]
score = pred[0][idx] * 100

if 281 <= idx <= 285:
    label = "Cat"
elif 151 <= idx <= 268:
    label = "Dog"
else:
    label = "Neither"

print("Name:", name)
print("Label:", label)
print("Accuracy:", round(float(score), 2), "%")
