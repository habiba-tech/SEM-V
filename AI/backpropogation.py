import math

# Sigmoid function
def sigmoid(x):
    return 1 / (1 + math.exp(-x))

# Derivative of sigmoid
def sigmoid_derivative(x):
    return x * (1 - x)


# Input and target
x = 1
y = 4

# Initial weights and biases
w1 = 0.5
w2 = 0.5
b1 = 0.1
b2 = 0.1

# Learning rate
eta = 0.1

# Training
for epoch in range(1000):

    # -------- FORWARD PASS --------
    z1 = x * w1 + b1
    a1 = sigmoid(z1)

    z2 = a1 * w2 + b2
    y_pred = z2

    # -------- LOSS FUNCTION --------
    loss = 0.5 * (y_pred - y) ** 2

    # -------- BACKWARD PASS --------
    error = y_pred - y

    # Output layer gradients
    dw2 = error * a1
    db2 = error

    # Hidden layer gradients
    delta1 = error * w2 * sigmoid_derivative(a1)
    dw1 = delta1 * x
    db1 = delta1

    # -------- UPDATE WEIGHTS --------
    w2 = w2 - eta * dw2
    b2 = b2 - eta * db2

    w1 = w1 - eta * dw1
    b1 = b1 - eta * db1


# Final result
print("Final Prediction:", round(y_pred, 4))
print("Target:", y)
print("Final Loss:", round(loss, 4))

print("\nFinal Weights and Biases:")
print("w1 =", round(w1, 4))
print("w2 =", round(w2, 4))
print("b1 =", round(b1, 4))
print("b2 =", round(b2, 4))