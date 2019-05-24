package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQOT8601 {
    DBParm EQTACT_RD;
    brParm EQTACT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String K_BSZ_BANKID = "01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_COUNT = 0;
    double WS_PRE_QTY = 0;
    double WS_CLPT = 0;
    char WS_ACT_FLG = ' ';
    double WS_TOT_QTY = 0;
    char WS_EQ_TYP = ' ';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRACT EQRACT = new EQRACT();
    CICCUST CICCUST = new CICCUST();
    EQCO8601_OPT_8601 EQCO8601_OPT_8601 = new EQCO8601_OPT_8601();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQB0006_AWA_0006 EQB0006_AWA_0006;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQOT8601 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "EQB0006_AWA_0006>");
        EQB0006_AWA_0006 = (EQB0006_AWA_0006) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.EQ_BKID);
        if (EQB0006_AWA_0006.EQ_BKID.trim().length() == 0) {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT);
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CEP.TRC(SCCGWA, BPCPQORG.BR);
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
            if (BPCPQORG.VIL_TYP.equalsIgnoreCase(EQB0006_AWA_0006.EQ_BKID)) {
            } else {
                if (BPCPQORG.VIL_TYP.equalsIgnoreCase("00") 
                    && EQB0006_AWA_0006.EQ_BKID.equalsIgnoreCase("01")) {
                } else {
                    CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_BANKID_MUST_SAME);
                }
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        EQRACT.KEY.EQ_BKID = EQB0006_AWA_0006.EQ_BKID;
        if (EQB0006_AWA_0006.STA_MTH == '0') {
            WS_EQ_TYP = ' ';
        } else if (EQB0006_AWA_0006.STA_MTH == '1') {
            WS_EQ_TYP = '1';
        } else if (EQB0006_AWA_0006.STA_MTH == '2') {
            WS_EQ_TYP = '2';
        } else if (EQB0006_AWA_0006.STA_MTH == '3') {
            WS_EQ_TYP = '3';
        } else if (EQB0006_AWA_0006.STA_MTH == '4') {
            WS_EQ_TYP = '4';
        } else {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_INVALID_STA_MTH);
        }
        T000_SUM_QTY_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TOT_QTY);
        T000_STARTBR_EQTACT();
        if (pgmRtn) return;
        T000_READNEXT_EQTACT();
        if (pgmRtn) return;
        if (WS_ACT_FLG == 'Y') {
            WS_PRE_QTY = EQRACT.EQ_QTY;
            WS_COUNT += 1;
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_COUNT <= 10 
            && WS_ACT_FLG != 'N') {
            B060_01_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_EQTACT();
            if (pgmRtn) return;
            if (EQRACT.EQ_QTY < WS_PRE_QTY) {
                WS_PRE_QTY = EQRACT.EQ_QTY;
                WS_COUNT += 1;
            }
        }
        T000_ENDBR_EQTACT();
        if (pgmRtn) return;
    }
    public void B060_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_01_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCO8601_OPT_8601);
        CEP.TRC(SCCGWA, EQRACT.EQ_QTY);
        CEP.TRC(SCCGWA, WS_TOT_QTY);
        WS_CLPT = ( EQRACT.EQ_QTY / WS_TOT_QTY ) * 100;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCO8601_OPT_8601.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
        } else {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_CINO_NOTFND);
        }
        EQCO8601_OPT_8601.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCO8601_OPT_8601.EQ_AC = EQRACT.KEY.EQ_AC;
        EQCO8601_OPT_8601.CAP_QTY = EQRACT.EQ_QTY;
        EQCO8601_OPT_8601.CAP_CLPT = WS_CLPT;
        EQCO8601_OPT_8601.TEL_NO = EQRACT.TEL_NO;
        EQCO8601_OPT_8601.EQ_ADDR = EQRACT.EQ_ADDR;
        CEP.TRC(SCCGWA, EQCO8601_OPT_8601.EQ_AC);
        CEP.TRC(SCCGWA, EQCO8601_OPT_8601.CAP_QTY);
        CEP.TRC(SCCGWA, EQCO8601_OPT_8601.CAP_CLPT);
        CEP.TRC(SCCGWA, EQCO8601_OPT_8601.TEL_NO);
        CEP.TRC(SCCGWA, EQCO8601_OPT_8601.EQ_ADDR);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCO8601_OPT_8601);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_SUM_QTY_PROC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.set = "WS-TOT-QTY=SUM(EQ_QTY)";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND AC_STS = 'N'";
        IBS.GROUP(SCCGWA, EQRACT, this, EQTACT_RD);
    }
    public void T000_STARTBR_EQTACT() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND ( :WS_EQ_TYP = ' ' "
            + "OR EQ_TYP = :WS_EQ_TYP ) "
            + "AND AC_STS = 'N'";
        EQTACT_BR.rp.order = "EQ_QTY DESC";
        IBS.STARTBR(SCCGWA, EQRACT, this, EQTACT_BR);
    }
    public void T000_READNEXT_EQTACT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRACT, this, EQTACT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_EQTACT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTACT_BR);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
