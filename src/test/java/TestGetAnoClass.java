import com.huangyichun.core.MyTask;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Map;
import java.util.Set;

public class TestGetAnoClass {
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        Map<String, Object> anoBean = applicationContext.getBeansWithAnnotation(MyTask.class);
        Set<Map.Entry<String, Object>> entitySet = anoBean.entrySet();
        for (Map.Entry<String, Object> entry : entitySet) {
            Class<? extends Object> clazz = entry.getValue().getClass();//获取bean对象
            System.out.println("================" + clazz.getName());
            MyTask myTask = AnnotationUtils.findAnnotation(clazz, MyTask.class);
            System.out.println("===================" + myTask.getClass().toString());
        }
    }
}
