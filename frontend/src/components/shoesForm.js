import { Form, Divider, Input, InputNumber, Button } from "antd";
import '../styles/shoesForm.css';

function FormComponent() {
    const onSubmit = (values) => {
        console.log(values);
    };
    return (
        <div id="upload-container">
            <Form name="상품 업로드" onFinish={onSubmit}>
                <Divider />
                <Form.Item
                    label={<div className="upload-label">성별</div>}
                    name="seller"
                    rules={[{ required: true, message: '남성, 여성 중 선택해주세요.' }]}
                >
                    <Input className="upload-name" size="large" placeholder="남성, 여성 중 선택해주세요." />
                </Form.Item>
                <Divider />
                <Form.Item name="name" label={<div className="upload-label">사이즈</div>}
                    rules={[{ required: true, message: '찾으시고자 하는 사이즈를 입력해주세요.' }]}>
                    <Input className="upload-name" size="largest" placeholder="찾는 사이즈를 입력해주세요." />
                </Form.Item>
                <Divider />
                <Form.Item
                    name="price"
                    label={<div className="upload-label">상품 가격</div>}
                    rules={[{ required: true, message: '희망하는 가격을 입력해주세요.' }]}
                >
                    <InputNumber defaultValue={0} className='upload-price' size="large" />
                </Form.Item>
                <Divider />
                <Form.Item
                    name="description"
                    label={<div className="uplopad-label">키워드</div>}
                    rules={[{ required: true, message: "희망하고자 하는 키워드를 입력해주세요." }]}>
                    <Input.TextArea size="large" id="product-description" showCount maxLength={300}
                        placeholder="희망하고자 하는 키워드를 입력해주세요." />
                </Form.Item>
                <Form.Item>
                    <Button id="submit-button" size="large" htmlType="submit">
                        상품 추천받기
                    </Button>
                </Form.Item>
            </Form>
        </div>
    )
}

export default FormComponent;
