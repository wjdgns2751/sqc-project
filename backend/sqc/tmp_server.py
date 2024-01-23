from flask import Flask, request

app = Flask(__name__)

@app.route('/findShoes', methods=['POST'])
def find_shoes():
    # Assuming the client sends JSON data in the request body
    data = request.json

    # Process the data as needed
    # For example, you can access data using data['key']

    # Create a response string
    start_id = '65af5dcb2a8ca81fdb0ce1fe'
    end_id = '65af5e526ec1f17d9dfee13b'
    response = f"startId: {start_id}, endId: {end_id}"

    return response

if __name__ == '__main__':
    app.run(host='localhost', port=5000, debug=True)
