package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMOT1300 {
    DBParm CMTPOSTC_RD;
    DBParm CMTPOSTD_RD;
    CMOT1300_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new CMOT1300_WS_TEMP_VARIABLE();
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    CMCA1300 CMCA1300 = new CMCA1300();
    CMCSEVEP CMCSEVEP = new CMCSEVEP();
    CMRPOSTC CMRPOSTC = new CMRPOSTC();
    CMRPOSTD CMRPOSTD = new CMRPOSTD();
    CMCF130 CMCF130 = new CMCF130();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMOT1300 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCA1300);
        WS_TEMP_VARIABLE.WS_LEN = 6794;
        CEP.TRC(SCCGWA, CMCA1300.IPT_DATE);
        CEP.TRC(SCCGWA, CMCA1300.OTH_KEY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B010_CHK_INPUT();
        }
        if (CMCA1300.STR_FLG == 'Y' 
            && CMCA1300.END_FLG == 'Y') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B050_MAIN_PROC();
            } else {
            }
        } else {
            B030_MAIN_PROC();
        }
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        if (CMCA1300.IPT_DATE == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_IPT_DT_ERR;
            S000_ERR_MSG_PROC();
        }
        if (CMCA1300.OTH_KEY.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_OTH_KEY_ERR;
            S000_ERR_MSG_PROC();
        }
        if (CMCA1300.EVE_CNT == 0 
            || CMCA1300.EVE_CNT > 10) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_EVENT_CNT_ERR;
            S000_ERR_MSG_PROC();
        }
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= CMCA1300.EVE_CNT; WS_TEMP_VARIABLE.WS_I += 1) {
            CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD);
            CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].EVENT_CD);
            CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].VAL_DT);
            if (CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.trim().length() == 0) {
                WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_PRDMO_CD_ERR;
                S000_ERR_MSG_PROC();
            }
            if (CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].EVENT_CD.trim().length() == 0) {
                WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_EVENT_CD_ERR;
                S000_ERR_MSG_PROC();
            }
            if (CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].CCY.trim().length() == 0) {
                WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_CCY_INP;
                S000_ERR_MSG_PROC();
            } else {
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].CCY;
                S000_CALL_BPZQCCY();
                if (BPCQCCY.RC.RTNCODE != 0) {
                    WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                    S000_ERR_MSG_PROC();
                }
            }
            if (CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_OLD != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_OLD;
                S000_CALL_BPZPQORG();
                if (BPCPQORG.RC.RC_CODE != 0) {
                    WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                    S000_ERR_MSG_PROC();
                }
            }
            if (CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_NEW != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_NEW;
                S000_CALL_BPZPQORG();
                if (BPCPQORG.RC.RC_CODE != 0) {
                    WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                    S000_ERR_MSG_PROC();
                }
            }
            if (CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_3 != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_3;
                S000_CALL_BPZPQORG();
                if (BPCPQORG.RC.RC_CODE != 0) {
                    WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                    S000_ERR_MSG_PROC();
                }
            }
            if (CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_4 != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_4;
                S000_CALL_BPZPQORG();
                if (BPCPQORG.RC.RC_CODE != 0) {
                    WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                    S000_ERR_MSG_PROC();
                }
            }
            if (CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_5 != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_5;
                S000_CALL_BPZPQORG();
                if (BPCPQORG.RC.RC_CODE != 0) {
                    WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                    S000_ERR_MSG_PROC();
                }
            }
            if (CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].VAL_DT == 0) {
                CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
        }
    }
    public void B030_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            T000_READ_CMTPOSTC();
            if (CMCA1300.STR_FLG == 'Y') {
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_VCHNO_EXIST;
                    S000_ERR_MSG_PROC();
                } else {
                    T000_WRITE_CMTPOSTC();
                }
            } else {
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    B030_01_CHK_POSTC_STS();
                } else {
                    WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_PSOTC_REC_NEXIST;
                    S000_ERR_MSG_PROC();
                }
            }
            T000_READUPD_CMTPOSTC();
            T000_GROUP_CMTPOSTD_MAX_SEQ();
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_REC_SEQ);
            for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= CMCA1300.EVE_CNT; WS_TEMP_VARIABLE.WS_I += 1) {
                WS_TEMP_VARIABLE.WS_REC_SEQ += 1;
                T000_WRITE_CMTPOSTD();
            }
            CMRPOSTC.TOT_CNT += CMCA1300.EVE_CNT;
            if (CMCA1300.END_FLG == 'Y') {
                CMRPOSTC.RECV_STS = 'N';
                T000_REWRITE_CMTPOSTC();
                IBS.init(SCCGWA, CMCSEVEP);
                CMCSEVEP.SYS_ID = SCCGWA.COMM_AREA.REQ_SYSTEM;
                CMCSEVEP.TX_DATE = CMCA1300.IPT_DATE;
                CMCSEVEP.VCH_NO = CMCA1300.OTH_KEY;
                S000_CALL_CMZSEVEP();
            } else {
                T000_REWRITE_CMTPOSTC();
            }
        } else {
        }
    }
    public void B050_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        CMCF130.CNT = 0;
        BPCPEBAS.DATA.OTHSYS_KEY = CMCA1300.OTH_KEY;
        S000_CALL_BPZPEBAS();
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= CMCA1300.EVE_CNT; WS_TEMP_VARIABLE.WS_I += 1) {
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD;
            BPCPOEWA.DATA.PROD_CODE = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].PRO_OLD;
            BPCPOEWA.DATA.EVENT_CODE = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].EVENT_CD;
            BPCPOEWA.DATA.BR_OLD = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_OLD;
            BPCPOEWA.DATA.BR_NEW = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_NEW;
            BPCPOEWA.DATA.BR_3 = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_3;
            BPCPOEWA.DATA.BR_4 = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_4;
            BPCPOEWA.DATA.BR_5 = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].BR_5;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].CCY;
            BPCPOEWA.DATA.VALUE_DATE = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].VAL_DT;
            for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 10; WS_TEMP_VARIABLE.WS_J += 1) {
                if (!IBS.isNumeric(CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].AT_ARRAY[WS_TEMP_VARIABLE.WS_J-1].AMT_NO+"") 
                    || !IBS.isNumeric(CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].AT_ARRAY[WS_TEMP_VARIABLE.WS_J-1].AMT_VAL+"")) {
                    WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_MUST_INP_NUM;
                    S000_ERR_MSG_PROC();
                }
                CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].AT_ARRAY[WS_TEMP_VARIABLE.WS_J-1].AMT_NO);
                CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].AT_ARRAY[WS_TEMP_VARIABLE.WS_J-1].AMT_VAL);
                if (CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].AT_ARRAY[WS_TEMP_VARIABLE.WS_J-1].AMT_VAL != 0 
                    && CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].AT_ARRAY[WS_TEMP_VARIABLE.WS_J-1].AMT_NO != 0) {
                    WS_TEMP_VARIABLE.WS_R = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].AT_ARRAY[WS_TEMP_VARIABLE.WS_J-1].AMT_NO;
                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_R);
                    BPCPOEWA.DATA.AMT_INFO[WS_TEMP_VARIABLE.WS_R-1].AMT = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].AT_ARRAY[WS_TEMP_VARIABLE.WS_J-1].AMT_VAL;
                }
            }
            BPCPOEWA.DATA.CI_NO = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].CI_NO;
            BPCPOEWA.DATA.POST_NARR = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].DESC;
            BPCPOEWA.DATA.DESC = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].RMK;
            BPCPOEWA.DATA.RVS_NO = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].RVS_NO;
            BPCPOEWA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.CLEAR_DATE;
            BPCPOEWA.DATA.OTH = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].CAT_OLD;
            S000_CALL_BPZPOEWA();
            CMCF130.CNT += 1;
            IBS.init(SCCGWA, CMCF130.ARRAY[CMCF130.CNT-1]);
            CMCF130.ARRAY[CMCF130.CNT-1].PRDMO_CD = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD;
            CMCF130.ARRAY[CMCF130.CNT-1].PROD_CD = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].PRO_OLD;
            CMCF130.ARRAY[CMCF130.CNT-1].EVENT_CD = CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1].EVENT_CD;
        }
        B050_OUTPUT_PROC();
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "35130";
        SCCFMT.ODM_FLG = 'Y';
        SCCFMT.DATA_PTR = CMCF130;
        SCCFMT.DATA_LEN = 30004;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_01_CHK_POSTC_STS() throws IOException,SQLException,Exception {
        if (CMRPOSTC.PROC_STS == 'N') {
            WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_VCH_PROC;
            S000_ERR_MSG_PROC();
        }
        if (CMRPOSTC.PROC_STS == 'C') {
            WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_VCH_CANCEL;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_CMTPOSTC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRPOSTC);
        CMRPOSTC.KEY.TX_ID = "0351300";
        CMRPOSTC.KEY.SYS_ID = SCCGWA.COMM_AREA.REQ_SYSTEM;
        CMRPOSTC.KEY.TX_DATE = CMCA1300.IPT_DATE;
        CMRPOSTC.KEY.VCH_NO = CMCA1300.OTH_KEY;
        CMTPOSTC_RD = new DBParm();
        CMTPOSTC_RD.TableName = "CMTPOSTC";
        IBS.READ(SCCGWA, CMRPOSTC, CMTPOSTC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READUPD_CMTPOSTC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRPOSTC);
        CMRPOSTC.KEY.TX_ID = "0351300";
        CMRPOSTC.KEY.SYS_ID = SCCGWA.COMM_AREA.REQ_SYSTEM;
        CMRPOSTC.KEY.TX_DATE = CMCA1300.IPT_DATE;
        CMRPOSTC.KEY.VCH_NO = CMCA1300.OTH_KEY;
        CMTPOSTC_RD = new DBParm();
        CMTPOSTC_RD.TableName = "CMTPOSTC";
        CMTPOSTC_RD.upd = true;
        IBS.READ(SCCGWA, CMRPOSTC, CMTPOSTC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_PSOTC_REC_NEXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_WRITE_CMTPOSTC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRPOSTC);
        CMRPOSTC.KEY.TX_ID = "0351300";
        CMRPOSTC.KEY.SYS_ID = SCCGWA.COMM_AREA.REQ_SYSTEM;
        CMRPOSTC.KEY.TX_DATE = CMCA1300.IPT_DATE;
        CMRPOSTC.KEY.VCH_NO = CMCA1300.OTH_KEY;
        CMRPOSTC.PROC_STS = 'P';
        CMRPOSTC.RECV_STS = 'P';
        CMRPOSTC.AC_DATE = 0;
        CMRPOSTC.JRN_NO = 0;
        CMRPOSTC.HOST_VCH_NO = " ";
        CMRPOSTC.TOT_CNT = 0;
        CMRPOSTC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRPOSTC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CMRPOSTC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRPOSTC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CMTPOSTC_RD = new DBParm();
        CMTPOSTC_RD.TableName = "CMTPOSTC";
        CMTPOSTC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CMRPOSTC, CMTPOSTC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_POSTC_REC_DUP;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_CMTPOSTC() throws IOException,SQLException,Exception {
        CMTPOSTC_RD = new DBParm();
        CMTPOSTC_RD.TableName = "CMTPOSTC";
        IBS.REWRITE(SCCGWA, CMRPOSTC, CMTPOSTC_RD);
    }
    public void T000_GROUP_CMTPOSTD_MAX_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRPOSTD);
        CMRPOSTD.KEY.TX_ID = "0351300";
        CMRPOSTD.KEY.SYS_ID = SCCGWA.COMM_AREA.REQ_SYSTEM;
        CMRPOSTD.KEY.TX_DATE = CMCA1300.IPT_DATE;
        CMRPOSTD.KEY.VCH_NO = CMCA1300.OTH_KEY;
        CMTPOSTD_RD = new DBParm();
        CMTPOSTD_RD.TableName = "CMTPOSTD";
        CMTPOSTD_RD.set = "POSTD-REC-SEQ=IFNULL(MAX(REC_SEQ),0)";
        CMTPOSTD_RD.where = "TX_ID = :CMRPOSTD.KEY.TX_ID "
            + "AND SYS_ID = :CMRPOSTD.KEY.SYS_ID "
            + "AND TX_DATE = :CMRPOSTD.KEY.TX_DATE "
            + "AND VCH_NO = :CMRPOSTD.KEY.VCH_NO";
        IBS.GROUP(SCCGWA, CMRPOSTD, this, CMTPOSTD_RD);
        WS_TEMP_VARIABLE.WS_REC_SEQ = CMRPOSTD.KEY.REC_SEQ;
    }
    public void T000_WRITE_CMTPOSTD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRPOSTD);
        CMRPOSTD.KEY.TX_ID = "0351300";
        CMRPOSTD.KEY.SYS_ID = SCCGWA.COMM_AREA.REQ_SYSTEM;
        CMRPOSTD.KEY.TX_DATE = CMCA1300.IPT_DATE;
        CMRPOSTD.KEY.VCH_NO = CMCA1300.OTH_KEY;
        CMRPOSTD.KEY.REC_SEQ = WS_TEMP_VARIABLE.WS_REC_SEQ;
        WS_TEMP_VARIABLE.WS_LEN = 6750;
        CMRPOSTD.BLOB_INP_DATA = IBS.CLS2CPY(SCCGWA, CMCA1300.EV_ARRAY[WS_TEMP_VARIABLE.WS_I-1]);
        CMTPOSTD_RD = new DBParm();
        CMTPOSTD_RD.TableName = "CMTPOSTD";
        CMTPOSTD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CMRPOSTD, CMTPOSTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_POSTD_REC_DUP;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CMZSEVEP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-EVE-POST", CMCSEVEP);
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
