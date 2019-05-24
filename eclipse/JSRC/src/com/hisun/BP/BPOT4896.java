package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4896 {
    String CPN_S_TSTS_MAINTAIN = "BP-S-TSTS-MAINTAIN  ";
    int K_MAX_CNT = 300;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    int WS_CNT = 0;
    char WS_END_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTSTS BPCSTSTS = new BPCSTSTS();
    SCCGWA SCCGWA;
    BPC4896 BPC4896;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4896 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC4896 = new BPC4896();
        IBS.init(SCCGWA, BPC4896);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC4896);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_STS_AUTH_INF_UPD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC4896.TSTS_APP);
        CEP.TRC(SCCGWA, BPC4896.TSTS_NO);
        CEP.TRC(SCCGWA, BPC4896.TABLE_NM);
        CEP.TRC(SCCGWA, BPC4896.PB_TYPE);
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].FLD_NO);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].TX_CLASS);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].RMK);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].REJ_CODE_COM);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].FLD_CTL1);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].FLD_CTL2);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].FLD_CTL3);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].FLD_CTL4);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].REJ_CODE_SYS);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].FLD_CTL5);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].FLD_CTL6);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].FLD_CTL7);
            CEP.TRC(SCCGWA, BPC4896.FLD_ARY[WS_I-1].FLD_CTL8);
            if (BPC4896.FLD_ARY[WS_I-1].FLD_NO < 0 
                || BPC4896.FLD_ARY[WS_I-1].FLD_NO > K_MAX_CNT) {
                CEP.TRC(SCCGWA, "AAAA           ");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                CEP.ERR(SCCGWA, WS_ERR_MSG, BPC4896.FLD_ARY[WS_I-1].FLD_NO);
            }
            if (BPC4896.FLD_ARY[WS_I-1].FLD_NO == 0) {
                if (BPC4896.FLD_ARY[WS_I-1].TX_CLASS != ' ' 
                    || BPC4896.FLD_ARY[WS_I-1].REJ_CODE_COM.trim().length() > 0 
                    || BPC4896.FLD_ARY[WS_I-1].REJ_CODE_SYS.trim().length() > 0 
                    || BPC4896.FLD_ARY[WS_I-1].FLD_CTL1 != ' ' 
                    || BPC4896.FLD_ARY[WS_I-1].FLD_CTL2 != ' ' 
                    || BPC4896.FLD_ARY[WS_I-1].FLD_CTL3 != ' ' 
                    || BPC4896.FLD_ARY[WS_I-1].FLD_CTL4 != ' ' 
                    || BPC4896.FLD_ARY[WS_I-1].FLD_CTL5 != ' ' 
                    || BPC4896.FLD_ARY[WS_I-1].FLD_CTL6 != ' ' 
                    || BPC4896.FLD_ARY[WS_I-1].FLD_CTL7 != ' ' 
                    || BPC4896.FLD_ARY[WS_I-1].FLD_CTL8 != ' ' 
                    || BPC4896.FLD_ARY[WS_I-1].RMK.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    CEP.TRC(SCCGWA, "BBBB           ");
                    CEP.ERR(SCCGWA, WS_ERR_MSG, BPC4896.FLD_ARY[WS_I-1].FLD_NO);
                }
            } else {
                WS_CNT += 1;
            }
        }
    }
    public void B020_STS_AUTH_INF_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTSTS);
        R000_TRANS_DATA();
        BPCSTSTS.FUNC = 'U';
        BPCSTSTS.UPDATE_FUNC = 'D';
        S000_CALL_BPZSTSTS();
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPCSTSTS.TSTS_APP = BPC4896.TSTS_APP;
        BPCSTSTS.TSTS_NO = BPC4896.TSTS_NO;
        BPCSTSTS.DESC = BPC4896.TABLE_NM;
        BPCSTSTS.PB_TYPE = BPC4896.PB_TYPE;
        BPCSTSTS.FLD_CNT = (short) WS_CNT;
        WS_CNT = 0;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (BPC4896.FLD_ARY[WS_I-1].FLD_NO != 0) {
                WS_CNT = WS_CNT + 1;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_NAME.FLD_NO = BPC4896.FLD_ARY[WS_I-1].FLD_NO;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_NAME.TX_CLASS = BPC4896.FLD_ARY[WS_I-1].TX_CLASS;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_NAME.REJ_CODE_COM = BPC4896.FLD_ARY[WS_I-1].REJ_CODE_COM;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_NAME.REJ_CODE_SYS = BPC4896.FLD_ARY[WS_I-1].REJ_CODE_SYS;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_CTL1 = BPC4896.FLD_ARY[WS_I-1].FLD_CTL1;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_CTL2 = BPC4896.FLD_ARY[WS_I-1].FLD_CTL2;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_CTL3 = BPC4896.FLD_ARY[WS_I-1].FLD_CTL3;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_CTL4 = BPC4896.FLD_ARY[WS_I-1].FLD_CTL4;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_CTL5 = BPC4896.FLD_ARY[WS_I-1].FLD_CTL5;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_CTL6 = BPC4896.FLD_ARY[WS_I-1].FLD_CTL6;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_CTL7 = BPC4896.FLD_ARY[WS_I-1].FLD_CTL7;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_CTL8 = BPC4896.FLD_ARY[WS_I-1].FLD_CTL8;
                BPCSTSTS.ITEM_LST_DATA[WS_CNT-1].FLD_RMK = BPC4896.FLD_ARY[WS_I-1].RMK;
            }
        }
    }
    public void S000_CALL_BPZSTSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TSTS_MAINTAIN, BPCSTSTS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
