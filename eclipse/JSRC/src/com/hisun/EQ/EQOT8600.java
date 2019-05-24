package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQOT8600 {
    brParm EQTACT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String K_BSZ_BANKID = "01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ACT_FLG = ' ';
    String WS_TOT_AC = " ";
    double WS_TOT_QTY = 0;
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRACT EQRACT = new EQRACT();
    EQCO8600_OPT_8600 EQCO8600_OPT_8600 = new EQCO8600_OPT_8600();
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
        CEP.TRC(SCCGWA, "EQOT8600 return!");
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
        if (EQB0006_AWA_0006.STA_MTH != ' ') {
            if (EQB0006_AWA_0006.STA_MTH == '1') {
                T000_STARTBR_EQTACT();
                if (pgmRtn) return;
                T000_READNEXT_EQTACT();
                if (pgmRtn) return;
                if (WS_ACT_FLG == 'Y') {
                    B060_01_OUT_TITLE();
                    if (pgmRtn) return;
                }
                while (WS_ACT_FLG != 'N' 
                    && SCCMPAG.FUNC != 'E') {
                    B060_01_OUT_BRW_DATA();
                    if (pgmRtn) return;
                    T000_READNEXT_EQTACT();
                    if (pgmRtn) return;
                }
                T000_ENDBR_EQTACT();
                if (pgmRtn) return;
            }
            if (EQB0006_AWA_0006.STA_MTH == '2') {
                T001_STARTBR_EQTACT();
                if (pgmRtn) return;
                T000_READNEXT_EQTACT();
                if (pgmRtn) return;
                if (WS_ACT_FLG == 'Y') {
                    B060_01_OUT_TITLE();
                    if (pgmRtn) return;
                }
                while (WS_ACT_FLG != 'N' 
                    && SCCMPAG.FUNC != 'E') {
                    B060_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                    T000_READNEXT_EQTACT();
                    if (pgmRtn) return;
                }
                T000_ENDBR_EQTACT();
                if (pgmRtn) return;
            }
        } else {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_INVALID_STA_MTH);
        }
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
        IBS.init(SCCGWA, EQCO8600_OPT_8600);
        CEP.TRC(SCCGWA, EQRACT.KEY.EQ_BKID);
        CEP.TRC(SCCGWA, EQRACT.EQ_TYP);
        CEP.TRC(SCCGWA, WS_TOT_AC);
        CEP.TRC(SCCGWA, WS_TOT_QTY);
        EQCO8600_OPT_8600.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCO8600_OPT_8600.ITEM = EQRACT.EQ_TYP;
        if (WS_TOT_AC.trim().length() == 0) EQCO8600_OPT_8600.TOT_AC = 0;
        else EQCO8600_OPT_8600.TOT_AC = Double.parseDouble(WS_TOT_AC);
        EQCO8600_OPT_8600.TOT_QTY = WS_TOT_QTY;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCO8600_OPT_8600);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCO8600_OPT_8600);
        CEP.TRC(SCCGWA, EQRACT.KEY.EQ_BKID);
        CEP.TRC(SCCGWA, EQRACT.ADD_BR);
        CEP.TRC(SCCGWA, WS_TOT_AC);
        CEP.TRC(SCCGWA, WS_TOT_QTY);
        EQCO8600_OPT_8600.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCO8600_OPT_8600.BR = EQRACT.ADD_BR;
        if (WS_TOT_AC.trim().length() == 0) EQCO8600_OPT_8600.TOT_AC = 0;
        else EQCO8600_OPT_8600.TOT_AC = Double.parseDouble(WS_TOT_AC);
        EQCO8600_OPT_8600.TOT_QTY = WS_TOT_QTY;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCO8600_OPT_8600);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_EQTACT() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.set = "WS-TOT-AC=COUNT(EQ_AC),WS-TOT-QTY=SUM(EQ_QTY)";
        EQTACT_BR.rp.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND AC_STS = 'N'";
        EQTACT_BR.rp.grp = "EQ_TYP";
        EQTACT_BR.rp.order = "EQ_TYP";
        IBS.STARTBR(SCCGWA, EQRACT, this, EQTACT_BR);
    }
    public void T001_STARTBR_EQTACT() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.set = "WS-TOT-AC=COUNT(EQ_AC),WS-TOT-QTY=SUM(EQ_QTY)";
        EQTACT_BR.rp.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND AC_STS = 'N'";
        EQTACT_BR.rp.grp = "ADD_BR";
        EQTACT_BR.rp.order = "ADD_BR";
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
