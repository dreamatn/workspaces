package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQOT8602 {
    DBParm EQTACT_RD;
    brParm EQTACTD_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 13;
    int K_MAX_COL = 500;
    String K_BSZ_BANKID = "01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_COUNT = 0;
    double WS_PRE_QTY = 0;
    double WS_CLPT = 0;
    char WS_ACT_FLG = ' ';
    char WS_ACTD_FLG = ' ';
    char WS_EQ_TYP = ' ';
    String WS_TRNSF_INAC = " ";
    String WS_TRNSF_OTAC = " ";
    int WS_BEGIN_DT = 0;
    int WS_END_DT = 0;
    char WS_TXN_TYP = ' ';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRACT EQRACT = new EQRACT();
    EQRACTD EQRACTD = new EQRACTD();
    CICCUST CICCUST = new CICCUST();
    EQCO8602_OPT_8602 EQCO8602_OPT_8602 = new EQCO8602_OPT_8602();
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
        CEP.TRC(SCCGWA, "EQOT8602 return!");
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
        EQRACTD.KEY.EQ_BKID = EQB0006_AWA_0006.EQ_BKID;
        WS_EQ_TYP = EQB0006_AWA_0006.EQ_TYP;
        WS_TRNSF_OTAC = EQB0006_AWA_0006.OUT_EQAC;
        WS_TRNSF_INAC = EQB0006_AWA_0006.IN_EQAC;
        WS_BEGIN_DT = EQB0006_AWA_0006.START_DT;
        WS_END_DT = EQB0006_AWA_0006.FIN_DT;
        CEP.TRC(SCCGWA, WS_EQ_TYP);
        CEP.TRC(SCCGWA, WS_TRNSF_OTAC);
        CEP.TRC(SCCGWA, WS_TRNSF_INAC);
        CEP.TRC(SCCGWA, WS_BEGIN_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        WS_TXN_TYP = '4';
        T000_STARTBR_EQTACTD();
        if (pgmRtn) return;
        T000_READNEXT_EQTACTD();
        if (pgmRtn) return;
        if (WS_ACTD_FLG == 'Y') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_COUNT <= 10 
            && WS_ACTD_FLG != 'N') {
            B060_01_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_EQTACTD();
            if (pgmRtn) return;
        }
        T000_ENDBR_EQTACTD();
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
        IBS.init(SCCGWA, EQCO8602_OPT_8602);
        EQCO8602_OPT_8602.EQ_BKID = EQRACTD.KEY.EQ_BKID;
        EQCO8602_OPT_8602.BR = EQRACTD.TXN_BR;
        CEP.TRC(SCCGWA, EQRACTD.KEY.EQ_AC);
        EQCO8602_OPT_8602.OUT_EQAC = EQRACTD.KEY.EQ_AC;
        IBS.init(SCCGWA, EQRACT);
        EQRACT.KEY.EQ_BKID = EQRACTD.KEY.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQRACTD.KEY.EQ_AC;
        CEP.TRC(SCCGWA, EQRACT.KEY.EQ_AC);
        T000_READ_EQTACT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, EQRACT.CI_NO);
        if (WS_ACT_FLG == 'Y') {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = EQRACT.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.RC.RC_CODE == 0) {
                EQCO8602_OPT_8602.OUT_CNM = CICCUST.O_DATA.O_CI_NM;
            } else {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_CINO_NOTFND);
            }
        }
        CEP.TRC(SCCGWA, EQCO8602_OPT_8602.OUT_CNM);
        CEP.TRC(SCCGWA, EQRACTD.OPP_AC);
        EQCO8602_OPT_8602.IN_EQAC = EQRACTD.OPP_AC;
        IBS.init(SCCGWA, EQRACT);
        EQRACT.KEY.EQ_BKID = EQRACTD.KEY.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQRACTD.OPP_AC;
        CEP.TRC(SCCGWA, EQRACT.KEY.EQ_AC);
        T000_READ_EQTACT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, EQRACT.KEY.EQ_AC);
        CEP.TRC(SCCGWA, EQRACT.CI_NO);
        if (WS_ACT_FLG == 'Y') {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = EQRACT.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.RC.RC_CODE == 0) {
                EQCO8602_OPT_8602.IN_CNM = CICCUST.O_DATA.O_CI_NM;
            } else {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_CINO_NOTFND);
            }
        }
        CEP.TRC(SCCGWA, EQCO8602_OPT_8602.IN_CNM);
        EQCO8602_OPT_8602.TXN_QTY = EQRACTD.TXN_QTY;
        EQCO8602_OPT_8602.REMARK = EQRACTD.REMARK;
        EQCO8602_OPT_8602.TXN_DATE = EQRACTD.KEY.TXN_DATE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCO8602_OPT_8602);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_EQTACT() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.eqWhere = "EQ_BKID,EQ_AC";
        IBS.READ(SCCGWA, EQRACT, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_STARTBR_EQTACTD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQRACTD.KEY.EQ_BKID);
        CEP.TRC(SCCGWA, WS_TXN_TYP);
        CEP.TRC(SCCGWA, WS_TRNSF_OTAC);
        CEP.TRC(SCCGWA, WS_TRNSF_INAC);
        CEP.TRC(SCCGWA, WS_BEGIN_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        EQTACTD_BR.rp = new DBParm();
        EQTACTD_BR.rp.TableName = "EQTACTD";
        EQTACTD_BR.rp.where = "EQ_BKID = :EQRACTD.KEY.EQ_BKID "
            + "AND ( :WS_EQ_TYP = ' ' "
            + "OR EQ_TYP = :WS_EQ_TYP ) "
            + "AND TXN_TYP = :WS_TXN_TYP "
            + "AND ( :WS_TRNSF_OTAC = ' ' "
            + "OR EQ_AC = :WS_TRNSF_OTAC ) "
            + "AND ( :WS_TRNSF_INAC = ' ' "
            + "OR OPP_AC = :WS_TRNSF_INAC ) "
            + "AND ( ( :WS_BEGIN_DT = 0 "
            + "AND :WS_END_DT = 0 ) "
            + "OR ( TXN_DATE BETWEEN :WS_BEGIN_DT "
            + "AND :WS_END_DT ) )";
        EQTACTD_BR.rp.order = "EQ_AC";
        IBS.STARTBR(SCCGWA, EQRACTD, this, EQTACTD_BR);
    }
    public void T000_READNEXT_EQTACTD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRACTD, this, EQTACTD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACTD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACTD_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_EQTACTD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTACTD_BR);
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
