package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZMANIP {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTMANIP_RD;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    String K_IBTMANIP = "IBTMANIP";
    char WS_OTH_AC_ATTR = ' ';
    int WS_OTH_BR = 0;
    String WS_OTH_CORP = " ";
    String WS_CORP = " ";
    short WS_I = 0;
    char WS_CONDITION_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICOCKOP AICOCKOP = new AICOCKOP();
    IBCQINF IBCQINF = new IBCQINF();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBCQIDNM IBCQIDNM = new IBCQIDNM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    IBRMANIP IBRMANIP = new IBRMANIP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    IBCMANAC IBCMANAC;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, IBCMANAC IBCMANAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCMANAC = IBCMANAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZMANIP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, SCCEXCP);
        WS_CONDITION_FLAG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        B020_GET_AC_INFO();
        B030_WRITE_DETAIL();
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCMANAC.AC_NO);
        CEP.TRC(SCCGWA, IBCMANAC.NOS_CD);
        if (IBCMANAC.NOS_CD.trim().length() == 0 
            && IBCMANAC.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
        }
        CEP.TRC(SCCGWA, IBCMANAC.CCY);
        if (IBCMANAC.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY_M);
        }
        CEP.TRC(SCCGWA, IBCMANAC.AMT);
        if (IBCMANAC.AMT == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IB_AMT_M_INPUT);
        } else {
            if (IBCMANAC.AMT < 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IB_AMT_INVALID);
            }
        }
        CEP.TRC(SCCGWA, IBCMANAC.SIGN);
        if (IBCMANAC.SIGN != 'C' 
            && IBCMANAC.SIGN != 'D') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.DRCR_SIGN_M);
        }
        B010_01_CHECK_OTH_AC();
        B010_02_CHECK_ACT_BR();
        B010_03_CHECK_VAL_DT();
    }
    public void B010_01_CHECK_OTH_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCMANAC.OTH_AC_TYP);
        if (IBCMANAC.OTH_AC_TYP == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTH_AC_ATTR_M);
        }
        CEP.TRC(SCCGWA, IBCMANAC.OTH_AC_NO);
        if (IBCMANAC.OTH_AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTH_AC_NO_M);
        } else {
            if (IBCMANAC.OTH_AC_NO.equalsIgnoreCase(IBCMANAC.AC_NO)) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.DRCR_AC_SAME);
            }
            if (IBCMANAC.OTH_AC_TYP == 'N') {
                IBS.init(SCCGWA, IBCQINF);
                IBCQINF.INPUT_DATA.AC_NO = IBCMANAC.OTH_AC_NO;
                S000_CALL_IBZQINF();
                if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
                }
                if (!IBCMANAC.CCY.equalsIgnoreCase(IBCQINF.INPUT_DATA.CCY)) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY);
                }
                if (IBCMANAC.SIGN == 'D' 
                    && IBCQINF.OUTPUT_DATA.VALUE_BAL < IBCMANAC.AMT 
                    && IBCQINF.OUTPUT_DATA.OD_FLAG == 'N') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_OD);
                }
                WS_OTH_AC_ATTR = IBCQINF.OUTPUT_DATA.AC_ATTR;
                WS_OTH_BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
            } else {
                if (IBCMANAC.OTH_AC_TYP == 'I') {
                    if (IBCMANAC.OTH_AC_NO == null) IBCMANAC.OTH_AC_NO = "";
                    JIBS_tmp_int = IBCMANAC.OTH_AC_NO.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) IBCMANAC.OTH_AC_NO += " ";
                    if (!IBCMANAC.CCY.equalsIgnoreCase(IBCMANAC.OTH_AC_NO.substring(7 - 1, 7 + 3 - 1))) {
                        CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY);
                    }
                }
                IBS.init(SCCGWA, AICPQMIB);
                AICPQMIB.INPUT_DATA.GL_BOOK = "BK001";
                AICPQMIB.INPUT_DATA.AC = IBCMANAC.OTH_AC_NO;
                S000_CALL_AIZPQMIB();
                if (AICPQMIB.OUTPUT_DATA.MANUAL_FLG == 'N') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_ACCOUNTED);
                }
                if (IBCMANAC.OTH_AC_NO == null) IBCMANAC.OTH_AC_NO = "";
                JIBS_tmp_int = IBCMANAC.OTH_AC_NO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) IBCMANAC.OTH_AC_NO += " ";
                if (IBCMANAC.OTH_AC_NO.substring(0, 6).trim().length() == 0) WS_OTH_BR = 0;
                else WS_OTH_BR = Integer.parseInt(IBCMANAC.OTH_AC_NO.substring(0, 6));
            }
        }
    }
    public void B010_02_CHECK_ACT_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCMANAC.POST_CTR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (IBCMANAC.POST_CTR == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.ACT_CTR);
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = IBCMANAC.POST_CTR;
            S000_CALL_BPZPQORG();
            if (BPCPQORG.ATTR != '2') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.ERR_BR_NOT_PQORG_ATTR);
            }
        }
    }
    public void B010_03_CHECK_VAL_DT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCMANAC.VAL_DATE);
        if (IBCMANAC.VAL_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.VAL_DATE_M);
        } else {
            IBS.init(SCCGWA, AICOCKOP);
            AICOCKOP.VAL_DATE = IBCMANAC.VAL_DATE;
            S000_CALL_AIZCKOP();
        }
    }
    public void B010_04_CHECK_TXNBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        S000_CALL_BPZPQORG();
        if (IBCQINF.OUTPUT_DATA.SWR_BR == '0') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else if (IBCQINF.OUTPUT_DATA.SWR_BR == '1') {
            if (BPCPORUP.DATA_INFO.TYP == null) BPCPORUP.DATA_INFO.TYP = "";
            JIBS_tmp_int = BPCPORUP.DATA_INFO.TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCPORUP.DATA_INFO.TYP += " ";
            if (BPCPQORG.TYP == null) BPCPQORG.TYP = "";
            JIBS_tmp_int = BPCPQORG.TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCPQORG.TYP += " ";
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR 
                && (!BPCPORUP.DATA_INFO.TYP.substring(0, 1).equalsIgnoreCase("0") 
                || BPCPQORG.TYP.substring(0, 1).equalsIgnoreCase("0"))) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else if (IBCQINF.OUTPUT_DATA.SWR_BR == '2') {
            if (BPCPQORG.TYP == null) BPCPQORG.TYP = "";
            JIBS_tmp_int = BPCPQORG.TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCPQORG.TYP += " ";
            if (BPCPORUP.DATA_INFO.TYP == null) BPCPORUP.DATA_INFO.TYP = "";
            JIBS_tmp_int = BPCPORUP.DATA_INFO.TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCPORUP.DATA_INFO.TYP += " ";
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR 
                && (!BPCPQORG.TYP.substring(0, 1).equalsIgnoreCase("0") 
                || BPCPORUP.DATA_INFO.TYP.substring(0, 1).equalsIgnoreCase("0"))) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
        }
    }
    public void B020_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        if (IBCMANAC.AC_NO.trim().length() > 0) {
            IBCQINF.INPUT_DATA.AC_NO = IBCMANAC.AC_NO;
        } else {
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBCMANAC.NOS_CD;
            IBCQINF.INPUT_DATA.CCY = IBCMANAC.CCY;
        }
        CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.AC_NO);
        CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.NOSTRO_CD);
        CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.CCY);
        S000_CALL_IBZQINF();
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_STS);
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.EFF_DATE);
        if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
        }
        if (!IBCMANAC.CCY.equalsIgnoreCase(IBCQINF.INPUT_DATA.CCY)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY);
        }
        if (IBCMANAC.SIGN == 'C' 
            && IBCQINF.OUTPUT_DATA.OD_FLAG == 'N') {
            if (IBCQINF.OUTPUT_DATA.VALUE_BAL < IBCMANAC.AMT) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_OD);
            }
        }
        if (IBCQINF.OUTPUT_DATA.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_UNEFF);
        }
        if (IBCQINF.OUTPUT_DATA.EFF_DATE > IBCMANAC.VAL_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TR_DATE);
        }
        if (SCCGWA.COMM_AREA.AC_DATE != IBCMANAC.VAL_DATE 
            && IBCQINF.OUTPUT_DATA.AC_ATTR == 'L') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.LAC_CHG_VAL_DATE);
        }
        B010_04_CHECK_TXNBR();
    }
    public void B020_01_CHECK_CORP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_OTH_AC_ATTR);
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_ATTR);
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.POST_ACT_CTR);
        CEP.TRC(SCCGWA, WS_OTH_BR);
        if (IBCQINF.OUTPUT_DATA.POST_ACT_CTR != WS_OTH_BR) {
            IBS.init(SCCGWA, IBCQIDNM);
            IBCQIDNM.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
            S000_CALL_IBZQIDNM();
            WS_CORP = IBCQIDNM.CORP_ID;
            CEP.TRC(SCCGWA, WS_OTH_BR);
            IBS.init(SCCGWA, IBCQIDNM);
            IBCQIDNM.BR = WS_OTH_BR;
            S000_CALL_IBZQIDNM();
            WS_OTH_CORP = IBCQIDNM.CORP_ID;
            CEP.TRC(SCCGWA, WS_OTH_CORP);
            CEP.TRC(SCCGWA, WS_CORP);
            if (!WS_CORP.equalsIgnoreCase(WS_OTH_CORP)) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOT_CORP);
            }
        }
    }
    public void B030_WRITE_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMANIP);
        IBRMANIP.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMANIP.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        IBRMANIP.STS = 'P';
        IBRMANIP.IPT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBRMANIP.IPT_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRMANIP.AC_NO = IBCMANAC.AC_NO;
        IBRMANIP.NOSTRO_CODE = IBCMANAC.NOS_CD;
        IBRMANIP.CCY = IBCMANAC.CCY;
        IBRMANIP.SIGN = IBCMANAC.SIGN;
        IBRMANIP.AMT = IBCMANAC.AMT;
        IBRMANIP.OTH_AC_TYP = IBCMANAC.OTH_AC_TYP;
        IBRMANIP.OTH_AC_NO = IBCMANAC.OTH_AC_NO;
        IBRMANIP.POST_CTR = IBCMANAC.POST_CTR;
        IBRMANIP.SUSPD_NO = IBCMANAC.SUSPD_NO;
        IBRMANIP.VAL_DATE = IBCMANAC.VAL_DATE;
        IBRMANIP.REF_NO = IBCMANAC.REF_NO;
        IBRMANIP.RMK = IBCMANAC.RMK;
        IBRMANIP.MMO = IBCMANAC.MMO;
        IBRMANIP.OTH_REF_NO = IBCMANAC.OTH_REF_NO;
        IBRMANIP.OTH_RMK = IBCMANAC.OTH_RMK;
        IBRMANIP.OTH_MMO = IBCMANAC.OTH_MMO;
        IBRMANIP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMANIP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) IBRMANIP.UPD_DATE = 0;
        else IBRMANIP.UPD_DATE = Integer.parseInt(SCCGWA.COMM_AREA.TL_ID);
        IBRMANIP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRMANIP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_IBTMANIP();
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZQIDNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CORP-ID-INQ", IBCQIDNM);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_LINK_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZCKOP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-OPEN-PERIOD", AICOCKOP);
        if (AICOCKOP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICOCKOP.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_WRITE_IBTMANIP() throws IOException,SQLException,Exception {
        IBTMANIP_RD = new DBParm();
        IBTMANIP_RD.TableName = "IBTMANIP";
        IBS.WRITE(SCCGWA, IBRMANIP, IBTMANIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMANIP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
