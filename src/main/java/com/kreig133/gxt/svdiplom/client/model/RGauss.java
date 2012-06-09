package com.kreig133.gxt.svdiplom.client.model;

import java.util.Random;

public class RGauss {

    private Random rand = new Random();

    final double matOzhidanie;
    final double razbros;

    public RGauss( double matOzhidanie, double razbros ) {
        this.matOzhidanie = matOzhidanie;
        this.razbros = razbros;
    }

    /**
     * Стандартным нормальным распределением
     * называется нормальное распределение с математическим
     * ожиданием 0 и стандартным отклонением 1.
     * Получим число принадлежащее этому распределению.
     *
     * @return
     */
    private double generate() {
        double s = 0.0;
        for ( int i = 0; i < 12; i++ ) {
            s += rand.nextDouble();
        }
        s -= 6.0;
        return s;
    }

    /*
    * На основе сгенерированного стандартного нормального
    * распределения получим нормальное распределение с необходимыми
    * нам параметрами. Для чего задаем мат. ожидание и интервал отклонения.
    * Чтобы определить дисперсию нужного нам нормального распределения
    * воспользуемся правилом трех сигм, т.е. поделим разброс на три, что и
    * будет нашим стандартным отклонением.
    */
    public double next() {
        final double next = generate() * ( razbros / 3.0 ) + matOzhidanie;
        return ( next >  ( matOzhidanie + razbros ) ) && ( next < ( matOzhidanie - razbros ) ) ? 
                ( next > matOzhidanie ? ( matOzhidanie + razbros ) : ( matOzhidanie - razbros ) ) : next ;
    }
}
