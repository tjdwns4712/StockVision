from flask import Flask, request, jsonify
import google.generativeai as genai
from PIL import Image
from io import BytesIO

app = Flask(__name__)

# API 키 설정
API_KEY = ""

# 생성 모델 초기화
genai.configure(api_key=API_KEY)
model = genai.GenerativeModel('gemini-pro-vision')

@app.route('/generate_text_from_image', methods=['POST'])
def generate_text_from_image():
    # 요청에서 이미지 데이터 가져오기
    image_data = request.files['image']
    
    # 이미지를 바이너리 데이터로 변환
    img_binary = image_data.read()
    
    # 이미지를 PIL Image 객체로 변환
    img = Image.open(BytesIO(img_binary))
    
    # 텍스트 입력
    text = request.form.get('text', '')  # 텍스트는 옵셔널
    
    # 이미지 분석 및 텍스트 요약 요청
    response = model.generate_content([text, img])
    
    return jsonify({'generated_text': response.text})

if __name__ == '__main__':
    app.run(debug=True)
