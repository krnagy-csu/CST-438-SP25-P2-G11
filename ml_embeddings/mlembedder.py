from sentence_transformers import SentenceTransformer
import json
import sys
import numpy as np
import math

def dot_product(A,B):
    dot = 0
    for num, num2 in zip(A, B):
        dot += num * num2
    return dot

def get_magnitude(A):
    magnitude = 0
    for num in A:
        magnitude += num**2
    
    return math.sqrt(magnitude)

def cosine_similarity(A, B):
    dot = dot_product(A, B)
    magnitude_a = get_magnitude(A)
    magnitude_b = get_magnitude(B)

    return dot / (magnitude_a * magnitude_b)

def similarity_list(tier_string, tier1_string):
    similarity = []
    current_rank = tier_string.split("^")
    current_rank1 = tier1_string.split("^")
    for rank, rank1 in zip(current_rank, current_rank1):
        embedding = model.encode([rank])[0]
        embedding1 = model.encode([rank1])[0]
        similarity.append(cosine_similarity(embedding, embedding1))

    return average_similarity(similarity)

        

def average_similarity(similarity_list):
    return sum(similarity_list) / len(similarity_list)


model = SentenceTransformer('sentence-transformers/all-MiniLM-L6-v2')
tier = sys.argv[1]
tier1 = sys.argv[2]
embedding = model.encode([tier])[0]
embedding1 = model.encode([tier1])[0]
# print(json.dumps(embedding))
average = similarity_list(tier, tier1)

print(average)



