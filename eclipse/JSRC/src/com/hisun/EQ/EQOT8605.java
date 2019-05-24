package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQOT8605 {
    int JIBS_tmp_int;
    DBParm EQTACT_RD;
    DBParm EQTBVT_RD;
    brParm EQTACTD_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 12;
    int K_MAX_COL = 500;
    int K_COL_CNT = 3;
    String K_BSZ_BANKID = "01";
    int WS_COUNT = 0;
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRACT EQRACT = new EQRACT();
    EQRACTD EQRACTD = new EQRACTD();
    EQRBVT EQRBVT = new EQRBVT();
    CICCUST CICCUST = new CICCUST();
    EQCO8605_OPT_8605 EQCO8605_OPT_8605 = new EQCO8605_OPT_8605();
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
        CEP.TRC(SCCGWA, "EQOT8605 return!");
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
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.EQ_ACT);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.EQ_CINO);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.EQ_AC);
        if (EQB0006_AWA_0006.EQ_ACT.trim().length() == 0 
            && EQB0006_AWA_0006.EQ_CINO.trim().length() == 0 
            && EQB0006_AWA_0006.EQ_AC.trim().length() == 0) {
            CEP.TRC(SCCGWA, "ACT CINO AC MUST INPUT ONE ");
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_INPUT_DATA_ERR);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        EQRACT.KEY.EQ_BKID = EQB0006_AWA_0006.EQ_BKID;
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.EQ_ACT);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.EQ_CINO);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.EQ_AC);
        if (EQB0006_AWA_0006.EQ_AC.trim().length() > 0) {
            EQRACT.KEY.EQ_AC = EQB0006_AWA_0006.EQ_AC;
            T000_READ_EQTACT_AC();
            if (pgmRtn) return;
        } else {
            if (EQB0006_AWA_0006.EQ_ACT.trim().length() > 0) {
                EQRACT.EQ_ACT = EQB0006_AWA_0006.EQ_ACT;
                T001_READ_EQTACT_ACT();
                if (pgmRtn) return;
            } else {
                if (EQB0006_AWA_0006.EQ_CINO.trim().length() > 0) {
                    EQRACT.EQ_CINO = EQB0006_AWA_0006.EQ_CINO;
                    T002_READ_EQTACT_CINO();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "ACT CINO AC MUST INPUT ONE ");
                    CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_CI_NO_MUST_INPUT);
                }
            }
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CI INF NOT FOUND");
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_CINO_NOTFND);
        }
        IBS.init(SCCGWA, EQRBVT);
        EQRBVT.KEY.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQRBVT.KEY.EQ_AC = EQRACT.KEY.EQ_AC;
        T020_READ_EQTBVT_AC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, EQRACTD);
        EQRACTD.KEY.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQRACTD.KEY.EQ_AC = EQRACT.KEY.EQ_AC;
        T010_STARTBR_EQTACTD();
        if (pgmRtn) return;
        T011_READNEXT_EQTACTD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "EQ ACTD INF NOT FOUND");
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_COUNT <= K_MAX_ROW) {
            R001_01_OUT_BRW_DATA();
            if (pgmRtn) return;
            T011_READNEXT_EQTACTD();
            if (pgmRtn) return;
        }
        T012_ENDBR_EQTACTD();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R001_01_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCO8605_OPT_8605);
        EQCO8605_OPT_8605.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCO8605_OPT_8605.EQ_TYP = EQRACT.EQ_TYP;
        EQCO8605_OPT_8605.EQ_ACT = EQRACT.EQ_ACT;
        EQCO8605_OPT_8605.EQ_CINO = EQRACT.EQ_CINO;
        EQCO8605_OPT_8605.EQ_AC = EQRACTD.KEY.EQ_AC;
        EQCO8605_OPT_8605.EQ_PSBK_NO = EQRBVT.KEY.PSBK_NO;
        EQCO8605_OPT_8605.EQ_CI_NO = EQRACT.CI_NO;
        EQCO8605_OPT_8605.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
        EQCO8605_OPT_8605.EQ_DT = EQRACTD.KEY.TXN_DATE;
        EQCO8605_OPT_8605.EQ_REMARK_60 = "" + EQRACTD.TXN_TYP;
        JIBS_tmp_int = EQCO8605_OPT_8605.EQ_REMARK_60.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) EQCO8605_OPT_8605.EQ_REMARK_60 = "0" + EQCO8605_OPT_8605.EQ_REMARK_60;
        if (EQRACTD.TXN_TYP == '3' 
            || EQRACTD.TXN_TYP == '4' 
            || EQRACTD.TXN_TYP == '6' 
            || EQRACTD.TXN_TYP == '7' 
            || EQRACTD.TXN_TYP == '0') {
            EQCO8605_OPT_8605.EQ_MIN_AMT = EQRACTD.TXN_QTY;
        } else {
            EQCO8605_OPT_8605.EQ_ADD_AMT = EQRACTD.TXN_QTY;
        }
        EQCO8605_OPT_8605.EQ_PRICE = EQRACTD.TXN_PRICE;
        EQCO8605_OPT_8605.EQ_AMT = EQRACTD.TXN_AMT;
        EQCO8605_OPT_8605.EQ_BAL_QTY = EQRACTD.BAL_QTY;
        EQCO8605_OPT_8605.EQ_REMARK = EQRACTD.REMARK;
        CEP.TRC(SCCGWA, EQRACTD.TXN_TYP);
        CEP.TRC(SCCGWA, EQCO8605_OPT_8605.EQ_MIN_AMT);
        CEP.TRC(SCCGWA, EQCO8605_OPT_8605.EQ_ADD_AMT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCO8605_OPT_8605);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_EQTACT_AC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.eqWhere = "EQ_BKID, EQ_AC";
        IBS.READ(SCCGWA, EQRACT, EQTACT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "EQTACT INF NOT FOUND");
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EQTACT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T001_READ_EQTACT_ACT() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.eqWhere = "EQ_BKID, EQ_ACT";
        IBS.READ(SCCGWA, EQRACT, EQTACT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "EQTACT INF NOT FOUND");
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EQTACT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T002_READ_EQTACT_CINO() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.eqWhere = "EQ_BKID, EQ_CINO";
        IBS.READ(SCCGWA, EQRACT, EQTACT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "EQTACT INF NOT FOUND");
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EQTACT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T020_READ_EQTBVT_AC() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.eqWhere = "EQ_BKID, EQ_AC";
        EQTBVT_RD.where = "PSBK_STS = 'N'";
        IBS.READ(SCCGWA, EQRBVT, this, EQTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "EQTACT INF NOT FOUND");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EQTACT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T010_STARTBR_EQTACTD() throws IOException,SQLException,Exception {
        EQTACTD_BR.rp = new DBParm();
        EQTACTD_BR.rp.TableName = "EQTACTD";
        EQTACTD_BR.rp.eqWhere = "EQ_BKID, EQ_AC";
        EQTACTD_BR.rp.order = "TXN_DATE";
        IBS.STARTBR(SCCGWA, EQRACTD, EQTACTD_BR);
    }
    public void T011_READNEXT_EQTACTD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRACTD, this, EQTACTD_BR);
    }
    public void T012_ENDBR_EQTACTD() throws IOException,SQLException,Exception {
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
