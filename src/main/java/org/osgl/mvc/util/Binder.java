package org.osgl.mvc.util;

import org.osgl.$;
import org.osgl.exception.NotAppliedException;
import org.osgl.util.C;
import org.osgl.util.S;

import java.util.Map;

/**
 * A {@code Binder} resolves to a certain type of argument out from a String-String map
 */
public abstract class Binder<T> extends $.F2<String, ParamValueProvider, T> {

    public abstract T resolve(String model, ParamValueProvider params);

    @Override
    public final T apply(String argName, ParamValueProvider params) throws NotAppliedException, $.Break {
        return resolve(argName, params);
    }

    // For primary types
    public static final Binder<boolean[]> PRIMITIVE_BOOLEAN_ARRAY = new Binder<boolean[]>() {
        @Override
        public boolean[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new boolean[0];
            int len = sa.length;
            boolean[] ba = new boolean[len];
            for (int i = 0; i < len; ++i) {
                ba[i] = Boolean.parseBoolean(sa[i]);
            }
            return ba;
        }
    };

    public static final Binder<Boolean[]> BOOLEAN_ARRAY = new Binder<Boolean[]>() {
        @Override
        public Boolean[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new Boolean[0];
            int len = sa.length;
            Boolean[] ba = new Boolean[len];
            for (int i = 0; i < len; ++i) {
                ba[i] = Boolean.valueOf(sa[i]);
            }
            return ba;
        }
    };

    public static final Binder<char[]> PRIMITIVE_CHAR_ARRAY = new Binder<char[]>() {
        @Override
        public char[] resolve(String model, ParamValueProvider params) {
            String concatenated = concatenate(model, params);
            if (null == concatenated) {
                return new char[0];
            }
            int len = concatenated.length();
            char[] ca = new char[len];
            for (int i = 0; i < len; ++i) {
                ca[i] = concatenated.charAt(i);
            }
            return ca;
        }
    };

    public static final Binder<Character[]> CHAR_ARRAY = new Binder<Character[]>() {
        @Override
        public Character[] resolve(String model, ParamValueProvider params) {
            String concatenated = concatenate(model, params);
            if (null == concatenated) {
                return new Character[0];
            }
            int len = concatenated.length();
            Character[] ca = new Character[len];
            for (int i = 0; i < len; ++i) {
                ca[i] = concatenated.charAt(i);
            }
            return ca;
        }
    };

    public static final Binder<byte[]> PRIMITIVE_BYTE_ARRAY = new Binder<byte[]>() {
        @Override
        public byte[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new byte[0];
            int len = sa.length;
            byte[] a = new byte[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = Byte.parseByte(s);
            }
            return a;
        }
    };

    public static final Binder<Byte[]> BYTE_ARRAY = new Binder<Byte[]>() {
        @Override
        public Byte[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new Byte[0];
            int len = sa.length;
            Byte[] a = new Byte[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Byte.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<short[]> PRIMITIVE_SHORT_ARRAY = new Binder<short[]>() {
        @Override
        public short[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new short[0];
            int len = sa.length;
            short[] a = new short[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = Short.parseShort(s);
            }
            return a;
        }
    };

    public static final Binder<Short[]> SHORT_ARRAY = new Binder<Short[]>() {
        @Override
        public Short[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new Short[0];
            int len = sa.length;
            Short[] a = new Short[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Short.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<int[]> PRIMITIVE_INT_ARRAY = new Binder<int[]>() {
        @Override
        public int[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new int[0];
            int len = sa.length;
            int[] a = new int[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = Integer.parseInt(s);
            }
            return a;
        }
    };

    public static final Binder<Integer[]> INT_ARRAY = new Binder<Integer[]>() {
        @Override
        public Integer[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new Integer[0];
            int len = sa.length;
            Integer[] a = new Integer[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Integer.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<long[]> PRIMITIVE_LONG_ARRAY = new Binder<long[]>() {
        @Override
        public long[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new long[0];
            int len = sa.length;
            long[] a = new long[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = Long.parseLong(s);
            }
            return a;
        }
    };

    public static final Binder<Long[]> LONG_ARRAY = new Binder<Long[]>() {
        @Override
        public Long[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new Long[0];
            int len = sa.length;
            Long[] a = new Long[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Long.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<float[]> PRIMITIVE_FLOAT_ARRAY = new Binder<float[]>() {
        @Override
        public float[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new float[0];
            int len = sa.length;
            float[] a = new float[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = Float.parseFloat(s);
            }
            return a;
        }
    };

    public static final Binder<Float[]> FLOAT_ARRAY = new Binder<Float[]>() {
        @Override
        public Float[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new Float[0];
            int len = sa.length;
            Float[] a = new Float[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Float.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<double[]> PRIMITIVE_DOUBLE_ARRAY = new Binder<double[]>() {
        @Override
        public double[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new double[0];
            int len = sa.length;
            double[] a = new double[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = Double.parseDouble(s);
            }
            return a;
        }
    };

    public static final Binder<Double[]> DOUBLE_ARRAY = new Binder<Double[]>() {
        @Override
        public Double[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return new Double[0];
            int len = sa.length;
            Double[] a = new Double[len];
            for (int i = 0; i < len; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Double.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<String[]> STRING_ARRAY = new Binder<String[]>() {
        @Override
        public String[] resolve(String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            return (null == sa) ? new String[0] : sa;
        }
    };

    private final static Map<Class, Binder> predefined = C.newMap(
            boolean[].class, PRIMITIVE_BOOLEAN_ARRAY,
            Boolean[].class, BOOLEAN_ARRAY,
            char[].class, PRIMITIVE_CHAR_ARRAY,
            Character[].class, CHAR_ARRAY,
            byte[].class, PRIMITIVE_BYTE_ARRAY,
            Byte[].class, BYTE_ARRAY,
            short[].class, PRIMITIVE_SHORT_ARRAY,
            Short[].class, SHORT_ARRAY,
            int[].class, PRIMITIVE_INT_ARRAY,
            Integer[].class, INT_ARRAY,
            float[].class, PRIMITIVE_FLOAT_ARRAY,
            Float[].class, FLOAT_ARRAY,
            long[].class, PRIMITIVE_LONG_ARRAY,
            Long[].class, LONG_ARRAY,
            double[].class, PRIMITIVE_DOUBLE_ARRAY,
            Double[].class, DOUBLE_ARRAY,
            String[].class, STRING_ARRAY
    );

    public static <T> void addPredefinedBinder(Class<T> type, Binder<T> resolver) {
        predefined.put(type, resolver);
    }

    public static Map<Class, Binder> predefined() {
        return C.map(predefined);
    }

    public static <T> Binder<T> predefined(Class<T> type) {
        return predefined.get(type);
    }

    private static String concatenate(String model, ParamValueProvider params) {
        String[] sa = params.paramVals(model);
        if (null == sa) {
            return null;
        }
        StringBuilder sb = S.builder();
        for (String s : sa) {
            sb.append(s);
        }
        return sb.toString();
    }
}
