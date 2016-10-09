package org.osgl.mvc.util;

import org.osgl.$;
import org.osgl.exception.NotAppliedException;
import org.osgl.util.C;
import org.osgl.util.Generics;
import org.osgl.util.S;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * A {@code Binder} resolves to a certain type of argument out from a String-String map
 */
public abstract class Binder<T> extends $.F3<T, String, ParamValueProvider, T> {

    protected Type targetType;

    public Binder() {
        exploreTargetType();
    }

    public Binder(Class<T> targetType) {
        this.targetType = $.notNull(targetType);
    }

    public Class<T> targetType() {
        return Generics.classOf(targetType);
    }

    public Type genericTargetType() {
        return targetType;
    }

    /**
     * Resolve bean from param value provider
     * @param bean cached bean instance from last conversation in the same session. Or `null`
     *             if not applied
     * @param model the name to specify the model
     * @param params the param value provider
     * @return the bean resolved
     */
    public abstract T resolve(T bean, String model, ParamValueProvider params);

    private void exploreTargetType() {
        List<Type> typeParams = Generics.typeParamImplementations(getClass(), Binder.class);
        targetType = typeParams.get(0);
    }

    @Override
    public final T apply(T bean, String argName, ParamValueProvider params) throws NotAppliedException, $.Break {
        return resolve(bean, argName, params);
    }

    // For primary types
    public static final Binder<boolean[]> PRIMITIVE_BOOLEAN_ARRAY = new Binder<boolean[]>() {
        @Override
        public boolean[] resolve(boolean[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return null;
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            boolean[] ba = new boolean[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, ba, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                ba[i] = Boolean.parseBoolean(sa[i]);
            }

            return ba;
        }
    };

    public static final Binder<Boolean[]> BOOLEAN_ARRAY = new Binder<Boolean[]>() {
        @Override
        public Boolean[] resolve(Boolean[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) return null;
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            Boolean[] ba = new Boolean[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, ba, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                ba[i] = Boolean.valueOf(sa[i]);
            }
            return ba;
        }
    };

    public static final Binder<char[]> PRIMITIVE_CHAR_ARRAY = new Binder<char[]>() {
        @Override
        public char[] resolve(char[] bean, String model, ParamValueProvider params) {
            String concatenated = concatenate(model, params);
            if (null == concatenated) {
                return null;
            }
            int len = concatenated.length();
            int oldLen = null == bean ? 0 : bean.length;
            char[] ca = new char[len];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, ca, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                ca[i] = concatenated.charAt(i);
            }
            return ca;
        }
    };

    public static final Binder<Character[]> CHAR_ARRAY = new Binder<Character[]>() {
        @Override
        public Character[] resolve(Character[] bean, String model, ParamValueProvider params) {
            String concatenated = concatenate(model, params);
            if (null == concatenated) {
                return null;
            }
            int len = concatenated.length();
            int oldLen = null == bean ? 0 : bean.length;
            Character[] ca = new Character[len];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, ca, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                ca[i] = concatenated.charAt(i);
            }
            return ca;
        }
    };

    public static final Binder<byte[]> PRIMITIVE_BYTE_ARRAY = new Binder<byte[]>() {
        @Override
        public byte[] resolve(byte[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            byte[] a = new byte[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = Byte.parseByte(s);
            }
            return a;
        }
    };

    public static final Binder<Byte[]> BYTE_ARRAY = new Binder<Byte[]>() {
        @Override
        public Byte[] resolve(Byte[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            Byte[] a = new Byte[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Byte.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<short[]> PRIMITIVE_SHORT_ARRAY = new Binder<short[]>() {
        @Override
        public short[] resolve(short[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            short[] a = new short[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = Short.parseShort(s);
            }
            return a;
        }
    };

    public static final Binder<Short[]> SHORT_ARRAY = new Binder<Short[]>() {
        @Override
        public Short[] resolve(Short[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            Short[] a = new Short[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Short.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<int[]> PRIMITIVE_INT_ARRAY = new Binder<int[]>() {
        @Override
        public int[] resolve(int[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            int[] a = new int[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = Integer.parseInt(s);
            }
            return a;
        }
    };

    public static final Binder<Integer[]> INT_ARRAY = new Binder<Integer[]>() {
        @Override
        public Integer[] resolve(Integer[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            Integer[] a = new Integer[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Integer.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<long[]> PRIMITIVE_LONG_ARRAY = new Binder<long[]>() {
        @Override
        public long[] resolve(long[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            long[] a = new long[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = Long.parseLong(s);
            }
            return a;
        }
    };

    public static final Binder<Long[]> LONG_ARRAY = new Binder<Long[]>() {
        @Override
        public Long[] resolve(Long[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            Long[] a = new Long[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Long.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<float[]> PRIMITIVE_FLOAT_ARRAY = new Binder<float[]>() {
        @Override
        public float[] resolve(float[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            float[] a = new float[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = Float.parseFloat(s);
            }
            return a;
        }
    };

    public static final Binder<Float[]> FLOAT_ARRAY = new Binder<Float[]>() {
        @Override
        public Float[] resolve(Float[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            Float[] a = new Float[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Float.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<double[]> PRIMITIVE_DOUBLE_ARRAY = new Binder<double[]>() {
        @Override
        public double[] resolve(double[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            double[] a = new double[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = Double.parseDouble(s);
            }
            return a;
        }
    };

    public static final Binder<Double[]> DOUBLE_ARRAY = new Binder<Double[]>() {
        @Override
        public Double[] resolve(Double[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            if (null == sa) {
                return null;
            }
            int len = sa.length;
            int oldLen = null == bean ? 0 : bean.length;
            Double[] a = new Double[len + oldLen];
            if (oldLen > 0) {
                System.arraycopy(bean, 0, a, 0, oldLen);
            }
            for (int i = oldLen; i < len + oldLen; ++i) {
                String s = sa[i];
                a[i] = null == s ? null : Double.valueOf(s);
            }
            return a;
        }
    };

    public static final Binder<String[]> STRING_ARRAY = new Binder<String[]>() {
        @Override
        public String[] resolve(String[] bean, String model, ParamValueProvider params) {
            String[] sa = params.paramVals(model);
            int oldLen = null == bean ? 0 : bean.length;
            if (oldLen > 0) {
                int len = sa.length;
                String[] a = new String[len + oldLen];
                System.arraycopy(bean, 0, a, 0, oldLen);
                System.arraycopy(sa, 0, a, oldLen, len);
                return a;
            }
            return sa;
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
