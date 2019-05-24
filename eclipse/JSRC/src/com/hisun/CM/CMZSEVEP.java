package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZSEVEP {
    brParm CMTPOSTD_BR = new brParm();
    DBParm CMTPOSTC_RD;
    DBParm CMTPOSTD_RD;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    short WS_R = 0;
    char WS_POSTD_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CMCA1300 CMCA1300 = new CMCA1300();
    CMRPOSTC CMRPOSTC = new CMRPOSTC();
    CMRPOSTD CMRPOSTD = new CMRPOSTD();
    CMCF130 CMCF130 = new CMCF130();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    CMCSEVEP CMCSEVEP;
    public void MP(SCCGWA SCCGWA, CMCSEVEP CMCSEVEP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCSEVEP = CMCSEVEP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMZSEVEP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CMCF130.CNT = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCSEVEP.SYS_ID);
        CEP.TRC(SCCGWA, CMCSEVEP.TX_DATE);
        CEP.TRC(SCCGWA, CMCSEVEP.VCH_NO);
        T000_READUPD_CMTPOSTC();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (CMRPOSTC.RECV_STS != 'N') {
                    WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_POST_RECV_STS;
                    S000_ERR_MSG_PROC();
                }
                if (CMRPOSTC.PROC_STS != 'P') {
                    WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_POST_PROC_STS;
                    S000_ERR_MSG_PROC();
                }
            } else {
                WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_PSOTC_REC_NEXIST;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (CMRPOSTC.RECV_STS == 'C') {
                    WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_POST_RECV_STS;
                    S000_ERR_MSG_PROC();
                }
                if (CMRPOSTC.PROC_STS != 'N') {
                    WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_POST_PROC_STS;
                    S000_ERR_MSG_PROC();
                }
            } else {
                WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_PSOTC_REC_NEXIST;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPCPEBAS);
        BPCPEBAS.DATA.OTHSYS_KEY = CMCSEVEP.VCH_NO;
        S000_CALL_BPZPEBAS();
        WS_POSTD_FLG = 'N';
        T000_STARTBR_CMTPOSTD();
        T000_READNEXT_CMTPOSTD();
        while (WS_POSTD_FLG != 'N') {
            B010_EVENT_PROC();
            T000_READNEXT_CMTPOSTD();
        }
        T000_ENDBR_CMTPOSTD();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CMRPOSTC.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CMRPOSTC.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CMRPOSTC.HOST_VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
            CMRPOSTC.PROC_STS = 'N';
            T000_REWRITE_CMTPOSTC();
        } else {
            CMRPOSTC.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CMRPOSTC.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CMRPOSTC.HOST_VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
            CMRPOSTC.PROC_STS = 'C';
            T000_REWRITE_CMTPOSTC();
        }
        B030_OUTPUT_PROC();
    }
    public void B010_EVENT_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, CMRPOSTD.BLOB_INP_DATA, CMCA1300.EV_ARRAY[1-1]);
        CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[1-1].PRDMO_CD);
        CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[1-1].PRO_OLD);
        CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[1-1].EVENT_CD);
        CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[1-1].BR_OLD);
        CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[1-1].BR_NEW);
        CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[1-1].CCY);
        CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[1-1].VAL_DT);
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = CMCA1300.EV_ARRAY[1-1].PRDMO_CD;
        BPCPOEWA.DATA.PROD_CODE = CMCA1300.EV_ARRAY[1-1].PRO_OLD;
        BPCPOEWA.DATA.EVENT_CODE = CMCA1300.EV_ARRAY[1-1].EVENT_CD;
        BPCPOEWA.DATA.BR_OLD = CMCA1300.EV_ARRAY[1-1].BR_OLD;
        BPCPOEWA.DATA.BR_NEW = CMCA1300.EV_ARRAY[1-1].BR_NEW;
        BPCPOEWA.DATA.BR_3 = CMCA1300.EV_ARRAY[1-1].BR_3;
        BPCPOEWA.DATA.BR_4 = CMCA1300.EV_ARRAY[1-1].BR_4;
        BPCPOEWA.DATA.BR_5 = CMCA1300.EV_ARRAY[1-1].BR_5;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = CMCA1300.EV_ARRAY[1-1].CCY;
        BPCPOEWA.DATA.VALUE_DATE = CMCA1300.EV_ARRAY[1-1].VAL_DT;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (!IBS.isNumeric(CMCA1300.EV_ARRAY[1-1].AT_ARRAY[WS_I-1].AMT_NO+"") 
                || !IBS.isNumeric(CMCA1300.EV_ARRAY[1-1].AT_ARRAY[WS_I-1].AMT_VAL+"")) {
                WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_MUST_INP_NUM;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[1-1].AT_ARRAY[WS_I-1].AMT_NO);
            CEP.TRC(SCCGWA, CMCA1300.EV_ARRAY[1-1].AT_ARRAY[WS_I-1].AMT_VAL);
            if (CMCA1300.EV_ARRAY[1-1].AT_ARRAY[WS_I-1].AMT_VAL != 0 
                && CMCA1300.EV_ARRAY[1-1].AT_ARRAY[WS_I-1].AMT_NO != 0) {
                WS_R = CMCA1300.EV_ARRAY[1-1].AT_ARRAY[WS_I-1].AMT_NO;
                CEP.TRC(SCCGWA, WS_R);
                BPCPOEWA.DATA.AMT_INFO[WS_R-1].AMT = CMCA1300.EV_ARRAY[1-1].AT_ARRAY[WS_I-1].AMT_VAL;
            }
        }
        BPCPOEWA.DATA.CI_NO = CMCA1300.EV_ARRAY[1-1].CI_NO;
        BPCPOEWA.DATA.POST_NARR = CMCA1300.EV_ARRAY[1-1].DESC;
        BPCPOEWA.DATA.DESC = CMCA1300.EV_ARRAY[1-1].RMK;
        BPCPOEWA.DATA.RVS_NO = CMCA1300.EV_ARRAY[1-1].RVS_NO;
        BPCPOEWA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.CLEAR_DATE;
        BPCPOEWA.DATA.OTH = CMCA1300.EV_ARRAY[1-1].CAT_OLD;
        S000_CALL_BPZPOEWA();
        CMCF130.CNT += 1;
        IBS.init(SCCGWA, CMCF130.ARRAY[CMCF130.CNT-1]);
        CMCF130.ARRAY[CMCF130.CNT-1].PRDMO_CD = CMCA1300.EV_ARRAY[1-1].PRDMO_CD;
        CMCF130.ARRAY[CMCF130.CNT-1].PROD_CD = CMCA1300.EV_ARRAY[1-1].PRO_OLD;
        CMCF130.ARRAY[CMCF130.CNT-1].EVENT_CD = CMCA1300.EV_ARRAY[1-1].EVENT_CD;
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "35130";
        SCCFMT.ODM_FLG = 'Y';
        SCCFMT.DATA_PTR = CMCF130;
        SCCFMT.DATA_LEN = 30004;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_CMTPOSTD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRPOSTD);
        CMRPOSTD.KEY.TX_ID = "0351300";
        CMRPOSTD.KEY.SYS_ID = CMCSEVEP.SYS_ID;
        CMRPOSTD.KEY.TX_DATE = CMCSEVEP.TX_DATE;
        CMRPOSTD.KEY.VCH_NO = CMCSEVEP.VCH_NO;
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.TX_ID);
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.SYS_ID);
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.TX_DATE);
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.VCH_NO);
        CMTPOSTD_BR.rp = new DBParm();
        CMTPOSTD_BR.rp.TableName = "CMTPOSTD";
        CMTPOSTD_BR.rp.where = "TX_ID = :CMRPOSTD.KEY.TX_ID "
            + "AND SYS_ID = :CMRPOSTD.KEY.SYS_ID "
            + "AND TX_DATE = :CMRPOSTD.KEY.TX_DATE "
            + "AND VCH_NO = :CMRPOSTD.KEY.VCH_NO";
        CMTPOSTD_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CMRPOSTD, this, CMTPOSTD_BR);
    }
    public void T000_READNEXT_CMTPOSTD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CMRPOSTD, this, CMTPOSTD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_POSTD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_POSTD_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_CMTPOSTD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CMTPOSTD_BR);
    }
    public void T000_READUPD_CMTPOSTC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRPOSTC);
        CMRPOSTC.KEY.TX_ID = "0351300";
        CMRPOSTC.KEY.SYS_ID = CMCSEVEP.SYS_ID;
        CMRPOSTC.KEY.TX_DATE = CMCSEVEP.TX_DATE;
        CMRPOSTC.KEY.VCH_NO = CMCSEVEP.VCH_NO;
        CMTPOSTC_RD = new DBParm();
        CMTPOSTC_RD.TableName = "CMTPOSTC";
        CMTPOSTC_RD.upd = true;
        IBS.READ(SCCGWA, CMRPOSTC, CMTPOSTC_RD);
    }
    public void T000_REWRITE_CMTPOSTC() throws IOException,SQLException,Exception {
        CMTPOSTC_RD = new DBParm();
        CMTPOSTC_RD.TableName = "CMTPOSTC";
        IBS.REWRITE(SCCGWA, CMRPOSTC, CMTPOSTC_RD);
    }
    public void T000_REWRITE_CMTPOSTD() throws IOException,SQLException,Exception {
        CMTPOSTD_RD = new DBParm();
        CMTPOSTD_RD.TableName = "CMTPOSTD";
        IBS.REWRITE(SCCGWA, CMRPOSTD, CMTPOSTD_RD);
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
