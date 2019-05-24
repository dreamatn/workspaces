package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2053 {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTTHIS_BR = new brParm();
    String K_OUTPUT_FMT = "BP357";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    int WS_LEN = 0;
    BPOT2053_WS_STR_POS WS_STR_POS = new BPOT2053_WS_STR_POS();
    char WS_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCF250 BPCF250 = new BPCF250();
    BPCO250 BPCO250 = new BPCO250();
    BPRTHIS BPRTHIS = new BPRTHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB2053_AWA_2053 BPB2053_AWA_2053;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2053 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2053_AWA_2053>");
        BPB2053_AWA_2053 = (BPB2053_AWA_2053) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_REC_PROC();
        B210_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2053_AWA_2053.TR_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR186);
        }
        IBS.CPY2CLS(SCCGWA, BPB2053_AWA_2053.STR_POS, WS_STR_POS);
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TEST");
        CEP.TRC(SCCGWA, BPB2053_AWA_2053.TR_DATE);
        CEP.TRC(SCCGWA, BPB2053_AWA_2053.VCH_NO);
        CEP.TRC(SCCGWA, BPB2053_AWA_2053.SEQ);
        CEP.TRC(SCCGWA, BPB2053_AWA_2053.APP_NO);
        CEP.TRC(SCCGWA, BPB2053_AWA_2053.STR_POS);
        CEP.TRC(SCCGWA, WS_STR_POS);
        IBS.init(SCCGWA, BPRTHIS);
        WS_CNT = 0;
        IBS.init(SCCGWA, BPCO250);
        BPRTHIS.KEY.DATE = BPB2053_AWA_2053.TR_DATE;
        BPRTHIS.KEY.VCH_NO = BPB2053_AWA_2053.VCH_NO;
        BPRTHIS.KEY.SEQ = BPB2053_AWA_2053.SEQ;
        BPRTHIS.APP_NO = BPB2053_AWA_2053.APP_NO;
        WS_CNT = 1;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_STR_POS);
        if (JIBS_tmp_str[0].trim().length() > 0) {
            CEP.TRC(SCCGWA, WS_STR_POS.WS_TR_DATE);
            CEP.TRC(SCCGWA, WS_STR_POS.WS_VCH_NO);
            CEP.TRC(SCCGWA, WS_STR_POS.WS_SEQ);
            IBS.init(SCCGWA, BPRTHIS);
            BPRTHIS.KEY.DATE = WS_STR_POS.WS_TR_DATE;
            BPRTHIS.KEY.VCH_NO = WS_STR_POS.WS_VCH_NO;
            BPRTHIS.KEY.SEQ = WS_STR_POS.WS_SEQ;
            BPRTHIS.APP_NO = BPB2053_AWA_2053.APP_NO;
            T000_STARTBR_BPTTHIS_BY_VAL();
        } else {
            T000_STARTBR_BPTTHIS();
        }
        T000_READNEXT_BPTTHIS();
        CEP.TRC(SCCGWA, "DEV1");
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, WS_FLG);
        while (WS_FLG != 'N' 
            && WS_CNT <= 99) {
            CEP.TRC(SCCGWA, BPRTHIS.CCY);
            CEP.TRC(SCCGWA, "WS-CNT");
            CEP.TRC(SCCGWA, WS_CNT);
            IBS.init(SCCGWA, WS_STR_POS);
            WS_STR_POS.WS_TR_DATE = BPRTHIS.KEY.DATE;
            WS_STR_POS.WS_VCH_NO = BPRTHIS.KEY.VCH_NO;
            WS_STR_POS.WS_SEQ = BPRTHIS.KEY.SEQ;
            R100_TRANS_DATA_OUPUT();
            T000_READNEXT_BPTTHIS();
        }
        CEP.TRC(SCCGWA, "DEV2");
        T000_ENDBR_BPTTHIS();
    }
    public void B210_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        BPCO250.TOD_REC_NUM = WS_CNT;
        BPCO250.END_POS = IBS.CLS2CPY(SCCGWA, WS_STR_POS);
        CEP.TRC(SCCGWA, BPCO250.TOD_REC_NUM);
        CEP.TRC(SCCGWA, BPCO250.END_POS);
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT < 50) {
            BPCO250.END_FLG = 'Y';
        } else {
            BPCO250.END_FLG = 'N';
        }
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO250;
        SCCFMT.DATA_LEN = 7960;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "NCB041701");
        CEP.TRC(SCCGWA, BPCO250);
    }
    public void T000_STARTBR_BPTTHIS() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        IBS.STARTBR(SCCGWA, BPRTHIS, BPTTHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARTBR BPTTHIS ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_BPTTHIS_BY_VAL() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        IBS.STARTBR(SCCGWA, BPRTHIS, BPTTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARTBR BPTTHIS ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_BPTTHIS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_BPTTHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTHIS_BR);
    }
    public void R100_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCO250.DATA[WS_CNT-1].TR_DATE = BPRTHIS.KEY.DATE;
        BPCO250.DATA[WS_CNT-1].VCH_NO = BPRTHIS.KEY.VCH_NO;
        BPCO250.DATA[WS_CNT-1].SEQ = BPRTHIS.KEY.SEQ;
        BPCO250.DATA[WS_CNT-1].APP_NO = BPRTHIS.APP_NO;
        BPCO250.DATA[WS_CNT-1].STS = BPRTHIS.STS;
        BPCO250.DATA[WS_CNT-1].CCY = BPRTHIS.CCY;
        BPCO250.DATA[WS_CNT-1].AMT = BPRTHIS.AMT;
        WS_CNT += 1;
    }
    public void S100_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
