package com.hisun.PY;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PYZRFPCI {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    char WS_FPCI_FLG = ' ';
    PYCMSG_ERROR_MSG PYCMSG_ERROR_MSG = new PYCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PYRFPCI PYRFPCI = new PYRFPCI();
    int WS_BEG_DATE = 0;
    int WS_END_DATE = 0;
    double WS_L_AMT = 0;
    double WS_H_AMT = 0;
    char WS_L_STS = ' ';
    char WS_H_STS = ' ';
    char WS_L_PAY_FLG = ' ';
    char WS_H_PAY_FLG = ' ';
    String WS_L_CLT_AC = " ";
    String WS_H_CLT_AC = " ";
    int WS_TX_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    PYCRFPCI PYCRFPCI;
    PYRFPCI PYRFPCI1;
    public void MP(SCCGWA SCCGWA, PYCRFPCI PYCRFPCI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PYCRFPCI = PYCRFPCI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PYZRFPCI return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        PYRFPCI1 = (PYRFPCI) PYCRFPCI.REC_PTR;
        PYCRFPCI.RETURN_INFO = 'F';
        IBS.init(SCCGWA, PYRFPCI);
        IBS.CLONE(SCCGWA, PYRFPCI1, PYRFPCI);
        PYCRFPCI.RC.RC_MMO = "PY";
        PYCRFPCI.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (PYCRFPCI.FUNC == 'C') {
            B010_WRITE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (PYCRFPCI.FUNC == 'U') {
            B020_READ_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (PYCRFPCI.FUNC == 'R') {
            B030_READ_PROC();
            if (pgmRtn) return;
        } else if (PYCRFPCI.FUNC == 'M') {
            B040_REWRITE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (PYCRFPCI.FUNC == 'S') {
            B050_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (PYCRFPCI.FUNC == 'T') {
            B060_STARTBR1_PROC();
            if (pgmRtn) return;
        } else if (PYCRFPCI.FUNC == 'R') {
            B070_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (PYCRFPCI.FUNC == 'E') {
            B080_ENDBR_PROC();
            if (pgmRtn) return;
        } else if (PYCRFPCI.FUNC == 'I') {
            B090_READ_PROC_FOR_CRINF();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + PYCRFPCI.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, PYRFPCI, PYRFPCI1);
    }
    public void B010_WRITE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
        if (WS_FPCI_FLG == 'D') {
            IBS.CPY2CLS(SCCGWA, PYCMSG_ERROR_MSG.PY_ERR_DUPKEY, PYCRFPCI.RC);
            PYCRFPCI.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, PYCMSG_ERROR_MSG.PY_FPCI_NOTFND, PYCRFPCI.RC);
            PYCRFPCI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
