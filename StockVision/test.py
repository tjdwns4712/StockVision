# import google.generativeai as genai

# # API 키 설정
# API_KEY = "AIzaSyD5f5KJnQwR-YLxBMFYyxTOI3aIWS6S3ro"

# # 생성 모델 초기화
# genai.configure(api_key=API_KEY)
# model = genai.GenerativeModel('gemini-pro')

# # 텍스트 생성
# response = model.generate_content("뉴스 가져와")
# print(response.text)


import google.generativeai as genai
import PIL.Image

import google.generativeai as genai
import PIL.Image

# API 키 설정
API_KEY = ""

# 생성 모델 초기화
genai.configure(api_key=API_KEY)
model = genai.GenerativeModel('gemini-pro-vision')

# 이미지 로드
image_path = "/Users/Zen1/leeseongjun/springboot/StockVision/static/stock_prediction.png"
img = PIL.Image.open(image_path)

# 텍스트 입력
text = "원유 가격 전망을 알려주세요."

# 이미지 분석 및 텍스트 요약 요청
response = model.generate_content(["한국어로 차트를 분석해줘",img])
print(response.text)
