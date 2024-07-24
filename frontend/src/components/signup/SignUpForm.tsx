import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import { z } from "zod"

import { Button } from "@/components/ui/button"
import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import { requestLogIn } from "@/api/requestLogIn"
import { requestSignUp } from "@/api/requestSignUp"

export const formSchemaSignUp = z.object({
    nickname: z.string().min(2, {
        message: "이름은 2글자 이상이어야 합니다."
    }),
    email: z.string().email({
        message: "올바른 이메일을 입력해주세요."
    }),
    password: z.string().min(6, {
        message: "비밀번호는 최소 6자 이상이어야 합니다."
    }).regex(/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]+$/, {
        message: "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다."
    }),
});

export default function SignUpForm() {

    const form = useForm<z.infer<typeof formSchemaSignUp>>({
        resolver: zodResolver(formSchemaSignUp),
        defaultValues: {
            email: "",
            password: "",
            nickname: "",
        },
    })

    function onSubmit(values: z.infer<typeof formSchemaSignUp>) {

        console.log(values);

        // TODO: send to server
        (async () => {
            const response = await requestSignUp(values); // 로그인 Request 전송
            const responseBody = await response.json();
            alert(responseBody.email +"님 환영합니다.");
            
            if (response.ok) {
                // 로그인 정보를 세션에 저장함.
                sessionStorage.setItem("user", JSON.stringify({
                    userId : responseBody.userId, 
                    email : responseBody.email, 
                    nickname : responseBody.nickname}));
                window.location.href = "/";
            }
        })(/* IIFE */);
        
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
                <FormField
                    control={form.control}
                    name="nickname"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Nickname</FormLabel>
                            <FormControl>
                                <Input placeholder="별명을 입력해주세요" {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />
                <FormField
                    control={form.control}
                    name="email"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Email</FormLabel>
                            <FormControl>
                                <Input type="email" placeholder="이메일 주소를 입력해주세요" {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />
                <FormField
                    control={form.control}
                    name="password"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Password</FormLabel>
                            <FormControl>
                                <Input type="password" placeholder="비밀번호를 입력해주세요" {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />
                <Button type="submit">회원가입</Button>
            </form>
        </Form>
    )
}