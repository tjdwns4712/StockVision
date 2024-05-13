import pandas as pd

# 엑셀 파일을 읽어 데이터프레임으로 저장
df = pd.read_excel("/Users/Zen1/leeseongjun/KOSDAQ.xlsx", header=None)

# 기업 이름과 종목 코드를 가져와 딕셔너리로 저장
company_tickers = dict(zip(df[1], df[0]))

# 딕셔너리의 값(종목 코드)에 ".KQ"를 추가하여 새로운 딕셔너리 생성
yfinance_tickers = {company: ticker + ".KQ" for company, ticker in company_tickers.items()}

# 수정된 종목 코드 출력
for company, ticker in yfinance_tickers.items():
    print(f"{company}: {ticker}")
