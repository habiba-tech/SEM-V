import matplotlib.pyplot as plt
from sklearn.svm import SVC
import numpy as np

# Hardcoded data (20 points)
X = np.array([
    [1,1], [2,1], [2,2], [3,1], [3,2],
    [4,2], [4,3], [5,2], [5,3], [6,3],
    [7,7], [8,7], [8,8], [9,7], [9,8],
    [10,8], [10,9], [11,9], [12,10], [11,10]
])

# Class labels
y = np.array([
    0,0,0,0,0,
    0,0,0,0,0,
    1,1,1,1,1,
    1,1,1,1,1
])

# Train SVM
model = SVC(kernel='linear')
model.fit(X, y)

# Define new points for prediction (Class 1 only)
new_points = np.array([
    [7,5],
    [10,6]
])
new_predictions = model.predict(new_points)

print("Predictions for new points (Class 1 only):")
for i, point in enumerate(new_points):
    print(f"  Point {point}: {new_predictions[i]}")

plt.figure(figsize=(8, 6))

# Plot the decision boundary
x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
xx, yy = np.meshgrid(np.arange(x_min, x_max, 0.1),
                     np.arange(y_min, y_max, 0.1))

Z = model.predict(np.c_[xx.ravel(), yy.ravel()])
Z = Z.reshape(xx.shape)

plt.contourf(xx, yy, Z, alpha=0.3, cmap=plt.cm.coolwarm)

# Plot original data points for Class 0 and Class 1 separately for cleaner legend
plt.scatter(X[y == 0, 0], X[y == 0, 1], c=plt.cm.coolwarm(0), edgecolors='k', s=80, zorder=2, label='Class 0 (Original Data)')
plt.scatter(X[y == 1, 0], X[y == 1, 1], c=plt.cm.coolwarm(1), edgecolors='k', s=80, zorder=2, label='Class 1 (Original Data)')

# Plot new predicted points
plt.scatter(new_points[:, 0], new_points[:, 1], c=new_predictions, cmap=plt.cm.coolwarm,
            marker='*', s=200, edgecolors='yellow', linewidth=2, zorder=3, label='Predicted Class 1 (Point)')

plt.xlabel("Feature 1")
plt.ylabel("Feature 2")
plt.title("SVM with Hardcoded Data and Decision Boundary (Class 1 Predictions Only)")
plt.grid(True)
plt.legend(title="Legend")
plt.show()
