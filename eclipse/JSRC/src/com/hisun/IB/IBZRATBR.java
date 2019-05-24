package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class IBZRATBR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    short K_360_DAYS = 360;
    short K_365_DAYS = 365;
    short K_366_DAYS = 366;
    double WS_TMP_RATE = 0;
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCQINF IBCQINF = new IBCQINF();
    IBCSORAT IBCSORAT = new IBCSORAT();
    BPCCINTI BPCCINTI = new BPCCINTI();
    DDCSLLCX DDCSLLCX = new DDCSLLCX();
    DDCOLLCX DDCOLLCX = new DDCOLLCX();
    IBRMST IBRMST = new IBRMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCRATBR IBCRATBR;
    public void MP(SCCGWA SCCGWA, IBCRATBR IBCRATBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCRATBR = IBCRATBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZRATBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBCRATBR.RC.RC_MMO = " ";
        IBCRATBR.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_RATE_INFO();
        if (pgmRtn) return;
        B030_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCRATBR.AC_NO);
        CEP.TRC(SCCGWA, IBCRATBR.NOS_CD);
        CEP.TRC(SCCGWA, IBCRATBR.CCY);
        if ((IBCRATBR.NOS_CD.trim().length() == 0 
            || IBCRATBR.CCY.trim().length() == 0) 
            && IBCRATBR.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBCRATBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_RATE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        if (IBCRATBR.AC_NO.trim().length() > 0) {
            IBCQINF.INPUT_DATA.AC_NO = IBCRATBR.AC_NO;
        } else {
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBCRATBR.NOS_CD;
            IBCQINF.INPUT_DATA.CCY = IBCRATBR.CCY;
        }
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
    }
    public void B030_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (IBCQINF.OUTPUT_DATA.RATE_MTH == '2') {
            B030_01_GET_RATE();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, IBCSORAT);
        IBCSORAT.NOST_CODE = IBCQINF.INPUT_DATA.NOSTRO_CD;
        IBCSORAT.CCY = IBCQINF.INPUT_DATA.CCY;
        IBCSORAT.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        IBCSORAT.PRV_FLAG = IBCQINF.OUTPUT_DATA.PRV_FLAG;
        IBCSORAT.RATE_FLAG = IBCQINF.OUTPUT_DATA.RATE_FLAG;
        IBCSORAT.OPEN_BR = IBCQINF.OUTPUT_DATA.OPEN_BR;
        if (IBCQINF.OUTPUT_DATA.AC_ATTR == 'N') {
            IBCSORAT.RATE_MTH = IBCQINF.OUTPUT_DATA.RATE_MTH;
            IBCSORAT.RATE_TYPE = IBCQINF.OUTPUT_DATA.RATE_TYPE;
            IBCSORAT.RATE_TERM = IBCQINF.OUTPUT_DATA.RATE_TERM;
            if (IBCQINF.OUTPUT_DATA.RATE_MTH == '2') {
                IBCSORAT.RATE_PCT = IBCQINF.OUTPUT_DATA.RATE_PCT;
                IBCSORAT.RATE_SPREAD = IBCQINF.OUTPUT_DATA.RATE_SPREAD;
                IBCSORAT.RATE = 0;
                IBCSORAT.TMP_RATE = WS_TMP_RATE;
            } else {
                IBCSORAT.RATE_PCT = 0;
                IBCSORAT.RATE_SPREAD = 0;
                IBCSORAT.RATE = IBCQINF.OUTPUT_DATA.RATE;
                IBCSORAT.TMP_RATE = IBCQINF.OUTPUT_DATA.RATE;
            }
        } else {
            B030_02_GET_L_INFO();
            if (pgmRtn) return;
        }
        IBCSORAT.OD_RATE = IBCQINF.OUTPUT_DATA.OD_RATE;
        if (IBCQINF.OUTPUT_DATA.CALR_STD == K_360_DAYS) {
            IBCSORAT.CALR_STD = "01";
        } else if (IBCQINF.OUTPUT_DATA.CALR_STD == K_365_DAYS) {
            IBCSORAT.CALR_STD = "02";
        } else if (IBCQINF.OUTPUT_DATA.CALR_STD == K_366_DAYS) {
            IBCSORAT.CALR_STD = "03";
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 128;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, IBCSORAT);
        SCCMPAG.DATA_LEN = 128;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_02_GET_L_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSLLCX);
        DDCSLLCX.FUNC = 'L';
        DDCSLLCX.AC = IBCQINF.OUTPUT_DATA.CORRAC_NO;
        S000_CALL_DDCSLLCX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_TYPE);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1);
        CEP.TRC(SCCGWA, DDCSLLCX.ADP_POST_DT);
        CEP.TRC(SCCGWA, DDCSLLCX.CON_RATE);
        if (DDCSLLCX.TIR_TYPE == 'N') {
            if (DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1 != 0) {
                IBCSORAT.RATE_MTH = '1';
                IBCSORAT.RATE = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1;
            } else {
                IBCSORAT.RATE_MTH = '2';
                IBCSORAT.RATE_TYPE = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1;
                IBCSORAT.RATE_TERM = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1;
                IBCSORAT.RATE_PCT = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR_PCT1;
                IBCSORAT.RATE_SPREAD = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1;
            }
            IBCSORAT.TMP_RATE = DDCSLLCX.CON_RATE;
        }
        CEP.TRC(SCCGWA, IBCSORAT.RATE_MTH);
        CEP.TRC(SCCGWA, IBCSORAT.RATE);
        CEP.TRC(SCCGWA, IBCSORAT.RATE_TYPE);
        CEP.TRC(SCCGWA, IBCSORAT.RATE_TERM);
        CEP.TRC(SCCGWA, IBCSORAT.RATE_SPREAD);
    }
    public void B030_01_GET_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        BPCCINTI.BASE_INFO.CCY = IBCQINF.INPUT_DATA.CCY;
        BPCCINTI.BASE_INFO.BASE_TYP = IBCQINF.OUTPUT_DATA.RATE_TYPE;
        BPCCINTI.BASE_INFO.TENOR = IBCQINF.OUTPUT_DATA.RATE_TERM;
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
        if (IBCQINF.OUTPUT_DATA.RATE_SPREAD != 0) {
            WS_TMP_RATE = BPCCINTI.BASE_INFO.RATE + IBCQINF.OUTPUT_DATA.RATE_SPREAD;
        } else {
            WS_TMP_RATE = BPCCINTI.BASE_INFO.RATE * ( 1 + IBCQINF.OUTPUT_DATA.RATE_PCT / 100 );
        }
        CEP.TRC(SCCGWA, WS_TMP_RATE);
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCRATBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDCSLLCX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-AC-LLCX", DDCSLLCX);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
