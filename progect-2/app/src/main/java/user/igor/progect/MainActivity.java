package user.igor.progect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // задание полей
    float robotprice = 35_000; // стоимость робота
    float amount = 1_700; // стипендия в месяц
    float account = 700; // счёт пользователя
    int percentFree = 0; // доля заработной платы на любые траты
    float percentBank = 9; // годовая процентная ставка за ипотеку
    float[] monthlyPayments = new float[60]; // создание массива ежемесячных платежей на 5 лет

    // создание дополнительных полей для вывода на экран полученных значений
    private TextView countOut; // поле вывода количества месяцев выплаты ипотеки
    private TextView manyMonthOut; // поле выписки по ежемесячным платежам

    // вывод на экран полученных значений

    // метод подсчёта времени выплаты ипотеки (годовой процент, зар.плата, свободные траты,
    // стоимость квартиры, накопления, массив для выписки по счёту)
    public int countMonth(float percentBankYear, float amount, int percent, float apartment, float accumulation, float[] arrayPayments) {

        float percentBankMonth = percentBankYear / 12; // подсчёт ежемесячного процента банка за ипотеку
        float mortgageCosts = (amount * percent) / 100; // подсчёт ежемесячных трат на ипотеку
        float total = apartment - accumulation; // подсчёт стоимости квартиры с учётом первоначального взноса
        int count = 0; // счётчик месяцев выплаты ипотеки

        // алгоритм расчёта ипотеки
        while (total > 0) {
            count++; // добавление нового месяца платежа
            total = (total + (total * percentBankMonth) / 100) - mortgageCosts; // вычисление долга с учётом выплаты и процента
            // заполнение массива ежемесячными платежами за ипотеку
            if (total > mortgageCosts) { // если сумма долга больше ежемесячного платежа, то
                arrayPayments[count - 1] = mortgageCosts; // в массив добавляется целый платёж
            } else { // иначе
                arrayPayments[count - 1] = total; // в массив добавляется платёж равный остатку долга
            }
        }

        return count;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { // создание жизненного цикла активности
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // присваивание жизненному циклу активити представления activity_main

        // присваивание переменным активити элементов представления activity_main
        countOut = findViewById(R.id.countOut); // вывод информации количества месяцев выплаты ипотеки
        manyMonthOut = findViewById(R.id.manyMonthOut); // вывод информации выписки по ежемесячным платежам

        // запонение экрана
        // 1) вывод количества месяцев выплаты ипотеки
        countOut.setText(countMonth(percentBank, amount, percentFree, robotprice, account, monthlyPayments) + " месяцев");
        // 2) подготовка выписки
        String monthlyPaymentsList = ""; // строка для записи выписки
        for (float list : monthlyPayments) { // цикл заполнения строки выпиской
            if (list > 0) {
                monthlyPaymentsList += Float.toString(list) + ", ";
            } else {
                break;
            }
        }
        // 3) вывод выписки ежемесячных выплат по ипотеке
        manyMonthOut.setText("Первоначальный взнос " + account + " монет, ежемесячные выплаты (монет): " + monthlyPaymentsList);
    }
}


