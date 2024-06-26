import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button"
import MainFeed from "./MainFeed";
import { getAccessToken } from "@/utils/Cookie";
import { useLogin, useUserInfo } from "@/utils/store";


const Main = () =>{
    //라우팅 네비게이터
    const nevigate = useNavigate();

    //글로벌 컨텍스트
    const { isLogin, setLogin } = useLogin();
    const { userInfo, setUserInfo } = useUserInfo();

    //초기 설정
    useEffect(() =>{
        if(!getAccessToken()){
            setLogin(false);
            setUserInfo({});
        }
    },[]);

    const doLogin = () =>{
        nevigate("/login");
    }

    const goSignup = () =>{
        nevigate("/signup");
    }

    return (
        isLogin ?
        (<MainFeed></MainFeed>) : 
        (<main className="flex h-screen">
            <section className=" flex-grow-[6] bg-[#6866EB] px-16 py-24 text-white">
                <h1 className="text-6xl font-bold">1inked 1n</h1>
                <div className="mt-48 text-4xl font-bold grid gap-4">
                    <h2>해외 취업 고민하는 우리</h2>
                    <h2>1inked 1n에서</h2>
                    <h2>자소서 첨삭부터</h2>
                    <h2>전문가 매칭까지</h2>
                </div>
            </section>
            <section className="flex-grow-[4] login flex flex-col items-center justify-center">
                <div className="w-full text-4xl font-bold text-white">
                    <h2 className="text-center text-black">Get Started</h2>
                    <div className="flex justify-center gap-4 mt-4">
                        <Button onClick={doLogin}className="bg-[#6866EB] w-48 hover:bg-violet-600">Login</Button>
                        <Button onClick={goSignup} className="bg-[#6866EB] w-48 hover:bg-violet-600">Signup</Button>
                    </div>
                </div>
            </section>
            </main>)
    );
};

export default Main;