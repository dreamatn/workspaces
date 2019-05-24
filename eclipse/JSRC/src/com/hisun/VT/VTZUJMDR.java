package com.hisun.VT;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class VTZUJMDR {
    brParm VTTJMDR_BR = new brParm();
    DBParm VTTJMAC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_P_INQ_PRD = "BP-P-QUERY-PRDT-INFO";
    String CPN_P_INQ_CCY = "BP-INQUIRE-CCY";
    String WS_ERR_MSG = " ";
    VTZUJMDR_WS_OTH WS_OTH = new VTZUJMDR_WS_OTH();
    int WS_BR = 0;
    VTZUJMDR_WS_OUTPUT WS_OUTPUT = new VTZUJMDR_WS_OUTPUT();
    VTZUJMDR_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new VTZUJMDR_WS_BROWSE_OUTPUT();
    char WS_JMCD_FLG = ' ';
    char WS_BR_MATCH_FLG = ' ';
    char WS_JMDR_FLG = ' ';
    char WS_JMAC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    VTRJMDR VTRJMDR = new VTRJMDR();
    VTRJMAC VTRJMAC = new VTRJMAC();
    VTRJMAC VTRNJMAC = new VTRJMAC();
    VTRJMAC VTROJMAC = new VTRJMAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCUJMDR VTCUJMDR;
    public void MP(SCCGWA SCCGWA, VTCUJMDR VTCUJMDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCUJMDR = VTCUJMDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZUJMDR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (VTCUJMDR.INPUT_DATA.FUNC == 'Q') {
        } else if (VTCUJMDR.INPUT_DATA.FUNC == 'A') {
            B010_CHECK_INPUT_ADD();
            if (pgmRtn) return;
            B020_MATCH_JMCD();
            if (pgmRtn) return;
            if (WS_JMCD_FLG == 'Y') {
                B030_REGISTER_JMAC();
                if (pgmRtn) return;
                B040_HISTORY_PROC();
                if (pgmRtn) return;
            }
        } else if (VTCUJMDR.INPUT_DATA.FUNC == 'Q') {
        } else if (VTCUJMDR.INPUT_DATA.FUNC == 'U') {
            B010_CHECK_INPUT_UPDATE();
            if (pgmRtn) return;
            B020_UPDATE_JMAC();
            if (pgmRtn) return;
            B040_HISTORY_PROC();
            if (pgmRtn) return;
        } else if (VTCUJMDR.INPUT_DATA.FUNC == 'D') {
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_ADD() throws IOException,SQLException,Exception {
        if (VTCUJMDR.INPUT_DATA.AC.trim().length() == 0 
            || VTCUJMDR.INPUT_DATA.PROD_CD.trim().length() == 0 
            || VTCUJMDR.INPUT_DATA.BR == 0 
            || VTCUJMDR.INPUT_DATA.CCY.trim().length() == 0) {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCUJMDR.INPUT_DATA.OTH.WEI_FLG == ' ' 
            && VTCUJMDR.INPUT_DATA.OTH.PEA_FLG == ' ' 
            && VTCUJMDR.INPUT_DATA.OTH.IIC_FLG == ' ' 
            && VTCUJMDR.INPUT_DATA.OTH.LOAN_LIMIT_FLG == ' ' 
            && VTCUJMDR.INPUT_DATA.OTH.OVER_FLG == ' ' 
            && VTCUJMDR.INPUT_DATA.OTH.NCB_FLG == ' ') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCUJMDR.INPUT_DATA.OTH.WEI_FLG != ' ' 
            && VTCUJMDR.INPUT_DATA.OTH.WEI_FLG != 'Y') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_WEI_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCUJMDR.INPUT_DATA.OTH.PEA_FLG != ' ' 
            && VTCUJMDR.INPUT_DATA.OTH.PEA_FLG != 'Y') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_PEA_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCUJMDR.INPUT_DATA.OTH.IIC_FLG != ' ' 
            && VTCUJMDR.INPUT_DATA.OTH.IIC_FLG != 'Y') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_IIC_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCUJMDR.INPUT_DATA.OTH.LOAN_LIMIT_FLG != ' ' 
            && VTCUJMDR.INPUT_DATA.OTH.LOAN_LIMIT_FLG != '1' 
            && VTCUJMDR.INPUT_DATA.OTH.LOAN_LIMIT_FLG != '2') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_LOAN_LIMIT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCUJMDR.INPUT_DATA.OTH.OVER_FLG != ' ' 
            && VTCUJMDR.INPUT_DATA.OTH.OVER_FLG != '1' 
            && VTCUJMDR.INPUT_DATA.OTH.OVER_FLG != '2') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_OVER_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCUJMDR.INPUT_DATA.OTH.NCB_FLG != ' ' 
            && VTCUJMDR.INPUT_DATA.OTH.NCB_FLG != 'Y') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_NCB_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = VTCUJMDR.INPUT_DATA.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (VTCUJMDR.INPUT_DATA.BR != 999999) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = VTCUJMDR.INPUT_DATA.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        if (!VTCUJMDR.INPUT_DATA.CCY.equalsIgnoreCase("999")) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = VTCUJMDR.INPUT_DATA.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRJMAC);
        VTRJMAC.KEY.AC = VTCUJMDR.INPUT_DATA.AC;
        VTRJMAC.STS = 'N';
        T000_READUP_VTTJMAC();
        if (pgmRtn) return;
        if (WS_JMAC_FLG == 'N') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_JMAC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, VTRJMAC, VTROJMAC);
    }
    public void B020_MATCH_JMCD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRJMDR);
        VTRJMDR.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        VTRJMDR.KEY.PROD_CD = VTCUJMDR.INPUT_DATA.PROD_CD;
        VTRJMDR.KEY.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRJMDR.KEY.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_JMCD_FLG = 'N';
        T000_STARTBR_VTTJMDR();
        if (pgmRtn) return;
        T000_READNEXT_VTTJMDR();
        if (pgmRtn) return;
        while (WS_JMDR_FLG != 'Y' 
            && WS_JMCD_FLG != 'Y') {
            IBS.init(SCCGWA, WS_OTH);
            WS_BR_MATCH_FLG = ' ';
            WS_BR_MATCH_FLG = 'N';
            if (VTRJMDR.KEY.BR != 999999 
                && VTRJMDR.KEY.BR != VTCUJMDR.INPUT_DATA.BR) {
                WS_BR = VTCUJMDR.INPUT_DATA.BR;
                R000_QUERY_BR_INFO();
                if (pgmRtn) return;
                if (BPCPQORG.SUPR_BR != VTRJMDR.KEY.BR) {
                    WS_BR = BPCPQORG.SUPR_BR;
                    R000_QUERY_BR_INFO();
                    if (pgmRtn) return;
                }
                if (BPCPQORG.SUPR_BR == VTRJMDR.KEY.BR) {
                    WS_BR_MATCH_FLG = 'Y';
                }
            } else {
                WS_BR_MATCH_FLG = 'Y';
            }
            if (WS_BR_MATCH_FLG == 'Y' 
                && (VTRJMDR.KEY.CCY.equalsIgnoreCase("999") 
                || VTRJMDR.KEY.CCY.equalsIgnoreCase(VTCUJMDR.INPUT_DATA.CCY))) {
                IBS.CPY2CLS(SCCGWA, VTRJMDR.KEY.OTH, WS_OTH);
                if ((WS_OTH.WS_WEI_FLG == ' ' 
                    || VTCUJMDR.INPUT_DATA.OTH.WEI_FLG == WS_OTH.WS_WEI_FLG) 
                    && (WS_OTH.WS_PEA_FLG == ' ' 
                    || VTCUJMDR.INPUT_DATA.OTH.PEA_FLG == WS_OTH.WS_PEA_FLG) 
                    && (WS_OTH.WS_IIC_FLG == ' ' 
                    || VTCUJMDR.INPUT_DATA.OTH.IIC_FLG == WS_OTH.WS_IIC_FLG) 
                    && (WS_OTH.WS_LOAN_LIMIT_FLG == ' ' 
                    || VTCUJMDR.INPUT_DATA.OTH.LOAN_LIMIT_FLG == WS_OTH.WS_LOAN_LIMIT_FLG) 
                    && (WS_OTH.WS_OVER_FLG == ' ' 
                    || VTCUJMDR.INPUT_DATA.OTH.OVER_FLG == WS_OTH.WS_OVER_FLG) 
                    && (WS_OTH.WS_NCB_FLG == ' ' 
                    || VTCUJMDR.INPUT_DATA.OTH.NCB_FLG == WS_OTH.WS_NCB_FLG)) {
                    WS_JMCD_FLG = 'Y';
                }
            }
            T000_READNEXT_VTTJMDR();
            if (pgmRtn) return;
        }
        T000_ENDBR_VTTJMDR();
        if (pgmRtn) return;
    }
    public void B020_UPDATE_JMAC() throws IOException,SQLException,Exception {
        VTRJMAC.STS = 'D';
        VTRJMAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.CLONE(SCCGWA, VTRJMAC, VTRNJMAC);
        T000_REWRITE_VTTJMAC();
        if (pgmRtn) return;
    }
    public void B030_REGISTER_JMAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRJMAC);
        IBS.init(SCCGWA, VTRNJMAC);
        VTRJMAC.KEY.AC = VTCUJMDR.INPUT_DATA.AC;
        VTRJMAC.KEY.REF_NO = VTCUJMDR.INPUT_DATA.REF;
        VTRJMAC.KEY.JM_CODE = VTRJMDR.JM_CODE;
        VTRJMAC.KEY.EFF_DATE = VTRJMDR.KEY.EFF_DATE;
        VTRJMAC.KEY.EXP_DATE = VTRJMDR.KEY.EXP_DATE;
        VTRJMAC.STS = 'N';
        VTRJMAC.BILL_FLG = VTRJMDR.BILL_FLG;
        VTRJMAC.TAX_FLG = VTRJMDR.TAX_FLG;
        VTRJMAC.TAX_TYPE = VTRJMDR.TAX_TYPE;
        VTRJMAC.FREE_TYPE = VTRJMDR.FREE_TYPE;
        VTRJMAC.BILL_LIM = VTRJMDR.BILL_LIM;
        VTRJMAC.YJ_TAX_TOT = 0;
        VTRJMAC.M_TAX_TOT = 0;
        VTRJMAC.SH_TAX_TOT = 0;
        VTRJMAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.CLONE(SCCGWA, VTRJMAC, VTRNJMAC);
        T000_WRITE_VTTJMAC();
        if (pgmRtn) return;
    }
    public void R000_QUERY_BR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
    }
    public void B040_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = "VTRJMAC";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = "JMAC DETAILS INFO ";
        BPCPNHIS.INFO.AC = VTRJMAC.KEY.AC;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 157;
        BPCPNHIS.INFO.NEW_DAT_PT = VTRNJMAC;
        BPCPNHIS.INFO.OLD_DAT_PT = VTROJMAC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_VTTJMDR() throws IOException,SQLException,Exception {
        VTTJMDR_BR.rp = new DBParm();
        VTTJMDR_BR.rp.TableName = "VTTJMDR";
        VTTJMDR_BR.rp.where = "IBS_AC_BK = :VTRJMDR.KEY.IBS_AC_BK "
            + "AND PROD_CD = :VTRJMDR.KEY.PROD_CD "
            + "AND EFF_DATE <= :VTRJMDR.KEY.EFF_DATE "
            + "AND EXP_DATE >= :VTRJMDR.KEY.EXP_DATE";
        VTTJMDR_BR.rp.order = "BR,CCY,OTH";
        IBS.STARTBR(SCCGWA, VTRJMDR, this, VTTJMDR_BR);
    }
    public void T000_READNEXT_VTTJMDR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, VTRJMDR, this, VTTJMDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_JMDR_FLG = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_ENDBR_VTTJMDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, VTTJMDR_BR);
    }
    public void T000_READUP_VTTJMAC() throws IOException,SQLException,Exception {
        VTTJMAC_RD = new DBParm();
        VTTJMAC_RD.TableName = "VTTJMAC";
        VTTJMAC_RD.eqWhere = "AC,STS";
        VTTJMAC_RD.upd = true;
        IBS.READ(SCCGWA, VTRJMAC, VTTJMAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_JMAC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_VTTJMAC() throws IOException,SQLException,Exception {
        VTTJMAC_RD = new DBParm();
        VTTJMAC_RD.TableName = "VTTJMAC";
        IBS.WRITE(SCCGWA, VTRJMAC, VTTJMAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_JMAC_ALREADY_EXIST;
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_VTTJMAC() throws IOException,SQLException,Exception {
        VTTJMAC_RD = new DBParm();
        VTTJMAC_RD.TableName = "VTTJMAC";
        IBS.REWRITE(SCCGWA, VTRJMAC, VTTJMAC_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PRD, BPCPQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.RC);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
