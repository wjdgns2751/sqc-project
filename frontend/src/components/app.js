import "../styles/app.css";
import MainComponent from "./shoesMain";
import FormComponent from "./shoesForm";
import ListComponent from "./shoesList";
import { Switch, Route, Link, useHistory } from "react-router-dom";
import { Button } from "antd";
import { DownloadOutlined } from "@ant-design/icons";

function App() {
  const history = useHistory();
  return (
    <div>
      <div id="header">
        <div id="header-area">
          <Link to="/">
            <img src="/images/icons/logo2.png" />
          </Link>
          <Button
            size="large"
            onClick={function () {
              history.push("/upload");
            }}
            icon={<DownloadOutlined />}
          >
            AI 추천받기
          </Button>
        </div>
      </div>
      <div id="body">
        <Switch>
          <Route exact={true} path="/">
            <MainComponent />
          </Route>
          <Route exact={true} path="/products/:id">
            <ListComponent />
          </Route>
          <Route exact={true} path="/upload">
            <FormComponent />
          </Route>
        </Switch>
      </div>
      <div id="footer"></div>
    </div>
  );
}

export default App;
