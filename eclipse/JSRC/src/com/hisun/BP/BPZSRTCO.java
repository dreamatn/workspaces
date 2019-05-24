package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSRTCO {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_BPTCORT = "BPTCORT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_IN_RECORD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCRRTAL BPCRRTAL = new BPCRRTAL();
    BPCSCORT BPCSCORT = new BPCSCORT();
    SCCGWA SCCGWA;
    BPCSRTCO BPCSRTCO;
    public void MP(SCCGWA SCCGWA, BPCSRTCO BPCSRTCO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSRTCO = BPCSRTCO;
        CEP.TRC(SCCGWA);
        S000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSRTCO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRRTAL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_PROC();
        if (pgmRtn) return;
        if (BPCSRTCO.FUNC == 'Q') {
            B100_SEARCH_PROC();
            if (pgmRtn) return;
        } else if (BPCSRTCO.FUNC == 'B') {
            B200_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCSRTCO.FUNC == 'A') {
            B300_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCSRTCO.FUNC == 'U') {
            B400_READUPD_PROC();
            if (pgmRtn) return;
        } else if (BPCSRTCO.FUNC == 'D') {
            B500_DELETE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCSRTCO.OUTPUT_INFO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_CCY);
        CEP.TRC(SCCGWA, BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_RBASE);
        CEP.TRC(SCCGWA, BPCSRTCO.OUTPUT_INFO.OUT_DATA);
    }
    public void B100_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK DATA");
        CEP.TRC(SCCGWA, BPCSRTCO.INP.INP_CCY);
        CEP.TRC(SCCGWA, BPCSRTCO.INP.INP_RBASE);
        CEP.TRC(SCCGWA, BPCSRTCO.INP.INP_RTENO);
        CEP.TRC(SCCGWA, BPCSRTCO.INP.INP_DESC);
        CEP.TRC(SCCGWA, BPCSRTCO.INP.INP_OLD_BASE);
        CEP.TRC(SCCGWA, BPCSRTCO.INP.INP_OLD_TYPE);
    }
    public void B100_SEARCH_PROC() throws IOException,SQLException,Exception {
        BPCRRTAL.FUNC = 'Q';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSRTCO.INP);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCRRTAL.INP);
        if (BPCSRTCO.QRYBR_INFO == 'T') {
            B701_STABR_QRYSTA_PROC();
            if (pgmRtn) return;
        } else if (BPCSRTCO.QRYBR_INFO == 'X') {
            B702_READ_QRYNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCSRTCO.QRYBR_INFO == 'D') {
            B703_END_QUERY_PROC();
            if (pgmRtn) return;
        } else if (BPCSRTCO.QRYBR_INFO == 'K') {
            BPCRRTAL.FUNC = 'Q';
            BPCRRTAL.QRYBR_INFO = 'K';
            BPCRRTAL.INP.INP_CCY = BPCSRTCO.INP.INP_CCY;
            BPCRRTAL.INP.INP_RBASE = BPCSRTCO.INP.INP_RBASE;
            BPCRRTAL.INP.INP_RTENO = BPCSRTCO.INP.INP_RTENO;
            S100_CALL_BPZRRTAL();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRRTAL.OUTPUT_INFO.OUT_DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSRTCO.OUTPUT_INFO.OUT_DATA);
        }
    }
    public void B200_BROWSE_PROC() throws IOException,SQLException,Exception {
        if (BPCSRTCO.READBR_INFO == 'S') {
            B601_STABR_BPTCORT_PROC();
            if (pgmRtn) return;
        } else if (BPCSRTCO.READBR_INFO == 'N') {
            B602_READ_NEXT_BPTCORT_PROC();
            if (pgmRtn) return;
        } else if (BPCSRTCO.READBR_INFO == 'E') {
            B603_END_BPTCORT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRRTAL);
        BPCRRTAL.FUNC = 'Q';
        BPCRRTAL.QRYBR_INFO = 'K';
        BPCRRTAL.INP.INP_CCY = BPCSRTCO.INP.INP_CCY;
        BPCRRTAL.INP.INP_RBASE = BPCSRTCO.INP.INP_RBASE;
        BPCRRTAL.INP.INP_RTENO = BPCSRTCO.INP.INP_RTENO;
        S100_CALL_BPZRRTAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRRTAL.RETURN_INFO);
        if (BPCRRTAL.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "11111");
            IBS.init(SCCGWA, BPCRRTAL);
            BPCRRTAL.FUNC = 'A';
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSRTCO.INP);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCRRTAL.INP);
            S100_CALL_BPZRRTAL();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "22222");
            BPCSRTCO.RETURN_INFO = 'X';
        }
    }
    public void B400_READUPD_PROC() throws IOException,SQLException,Exception {
        BPCRRTAL.FUNC = 'R';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSRTCO.INP);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCRRTAL.INP);
        S100_CALL_BPZRRTAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRRTAL.RETURN_INFO);
        if (BPCRRTAL.RETURN_INFO == 'N') {
            BPCSRTCO.RETURN_INFO = 'N';
        } else if (BPCRRTAL.RETURN_INFO == 'F') {
            B400_UPDATE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPTCORT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B400_UPDATE_PROC() throws IOException,SQLException,Exception {
        BPCRRTAL.FUNC = 'U';
        S100_CALL_BPZRRTAL();
        if (pgmRtn) return;
        if (BPCRRTAL.RETURN_INFO == 'F') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSRTCO.OUTPUT_INFO.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPTCORT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B500_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRRTAL);
        BPCRRTAL.INP.INP_CCY = BPCSRTCO.INP.INP_CCY;
        BPCRRTAL.INP.INP_RBASE = BPCSRTCO.INP.INP_RBASE;
        BPCRRTAL.INP.INP_RTENO = BPCSRTCO.INP.INP_RTENO;
        BPCRRTAL.FUNC = 'Q';
        BPCRRTAL.QRYBR_INFO = 'K';
        S100_CALL_BPZRRTAL();
        if (pgmRtn) return;
        BPCSRTCO.INP.INP_DESC = BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_DESC;
        BPCSRTCO.INP.INP_OLD_BASE = BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_BASE;
        BPCSRTCO.INP.INP_OLD_TYPE = BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_TYPE;
        BPCRRTAL.FUNC = 'R';
        S100_CALL_BPZRRTAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRRTAL);
        CEP.TRC(SCCGWA, BPCRRTAL.RETURN_INFO);
        if (BPCRRTAL.RETURN_INFO == 'N') {
            BPCSRTCO.RETURN_INFO = 'N';
        } else if (BPCRRTAL.RETURN_INFO == 'F') {
            BPCRRTAL.FUNC = 'D';
            S100_CALL_BPZRRTAL();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPTCORT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B601_STABR_BPTCORT_PROC() throws IOException,SQLException,Exception {
        BPCRRTAL.FUNC = 'B';
        BPCRRTAL.READBR_INFO = 'S';
        S100_CALL_BPZRRTAL();
        if (pgmRtn) return;
    }
    public void B602_READ_NEXT_BPTCORT_PROC() throws IOException,SQLException,Exception {
        BPCRRTAL.FUNC = 'B';
        BPCRRTAL.READBR_INFO = 'N';
        S100_CALL_BPZRRTAL();
        if (pgmRtn) return;
        if (BPCRRTAL.RETURN_INFO == 'E') {
            BPCSRTCO.RETURN_INFO = 'E';
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRRTAL.OUTPUT_INFO.OUT_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSRTCO.OUTPUT_INFO.OUT_DATA);
    }
    public void B603_END_BPTCORT_PROC() throws IOException,SQLException,Exception {
        BPCRRTAL.FUNC = 'B';
        BPCRRTAL.READBR_INFO = 'E';
        S100_CALL_BPZRRTAL();
        if (pgmRtn) return;
    }
    public void B701_STABR_QRYSTA_PROC() throws IOException,SQLException,Exception {
        BPCRRTAL.FUNC = 'Q';
        BPCRRTAL.QRYBR_INFO = 'T';
        S100_CALL_BPZRRTAL();
        if (pgmRtn) return;
    }
    public void B702_READ_QRYNEXT_PROC() throws IOException,SQLException,Exception {
        BPCRRTAL.FUNC = 'Q';
        BPCRRTAL.QRYBR_INFO = 'X';
        S100_CALL_BPZRRTAL();
        if (pgmRtn) return;
        if (BPCRRTAL.RETURN_INFO == 'N') {
            BPCSRTCO.RETURN_INFO = 'E';
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRRTAL.OUTPUT_INFO.OUT_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSRTCO.OUTPUT_INFO.OUT_DATA);
    }
    public void B703_END_QUERY_PROC() throws IOException,SQLException,Exception {
        BPCRRTAL.FUNC = 'Q';
        BPCRRTAL.QRYBR_INFO = 'D';
        S100_CALL_BPZRRTAL();
        if (pgmRtn) return;
    }
    public void S100_CALL_BPZRRTAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-AUDB-RT", BPCRRTAL);
        if (BPCRRTAL.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_REC_NOTFND, BPCSRTCO.OUTPUT_INFO.RC);
            BPCSRTCO.RETURN_INFO = 'N';
        } else if (BPCRRTAL.RETURN_INFO == 'F') {
            BPCSRTCO.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSRTCO.OUTPUT_INFO.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
