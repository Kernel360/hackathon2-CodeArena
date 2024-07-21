import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import LoginForm from "./LoginForm"
import { Separator } from "@/components/ui/separator"

export default function LoginCard() {
    return (
        <Card className=" flex flex-col w-[350px] items-center justify-center h-screen">
            <CardHeader>
                <CardTitle>로그인</CardTitle>
                <CardDescription>로그인 UI 입니다</CardDescription>
            </CardHeader>
            <Separator />
            <CardContent>
                <LoginForm />
            </CardContent>
        </Card>
    )
}
