package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class AIZRCMIB {
    boolean pgmRtn = false;
    String TBL_AITCMIB = "AITCMIB";
    String WS_TX_CD = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AIRCMIB AIRTMIB = new AIRCMIB();
    char WS_FIRST_FLAG = 'Y';
    char WS_PREV_OPER_FLAG = 'N';
    char WS_PREV_RESULT_FLAG = 'N';
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICRCMIB AICRCMIB;
    AIRCMIB AIRLCMIB;
    public void MP(SCCGWA SCCGWA, AICRCMIB AICRCMIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRCMIB = AICRCMIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRCMIB return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, AICRCMIB.RC);
        AIRLCMIB = (AIRCMIB) AICRCMIB.POINTER;
        IBS.init(SCCGWA, AIRCMIB);
        AICRCMIB.REC_LEN = 407;
        IBS.CLONE(SCCGWA, AIRLCMIB, AIRCMIB);
        if (WS_FIRST_FLAG == 'Y') {
            IBS.init(SCCGWA, AIRTMIB);
            WS_FIRST_FLAG = 'N';
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (AICRCMIB.FUNC == 'C') {
            WS_PREV_OPER_FLAG = 'N';
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCMIB.FUNC == 'R') {
            WS_PREV_OPER_FLAG = 'N';
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCMIB.FUNC == 'U') {
            WS_PREV_OPER_FLAG = 'N';
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCMIB.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            WS_PREV_OPER_FLAG = 'Y';
        } else if (AICRCMIB.FUNC == 'D') {
            WS_PREV_OPER_FLAG = 'N';
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCMIB.FUNC == 'B') {
            WS_PREV_OPER_FLAG = 'N';
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCMIB.FUNC == 'F') {
            B070_READFIR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            WS_PREV_OPER_FLAG = 'N';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRCMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRCMIB, AIRLCMIB);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITCMIB_RD = new DBParm();
        AITCMIB_RD.TableName = "AITCMIB";
        AITCMIB_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRCMIB, AITCMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRCMIB.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_CMIB_ALREADY_EXIST, AICRCMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITCMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        AITCMIB_RD = new DBParm();
        AITCMIB_RD.TableName = "AITCMIB";
        AITCMIB_RD.upd = true;
        IBS.READ(SCCGWA, AIRCMIB, AITCMIB_RD);
        CEP.TRC(SCCGWA, AIRCMIB.STS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITCMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITCMIB_RD = new DBParm();
        AITCMIB_RD.TableName = "AITCMIB";
        IBS.REWRITE(SCCGWA, AIRCMIB, AITCMIB_RD);
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRCMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.ITM);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
        CEP.TRC(SCCGWA, AIRTMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRTMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRTMIB.KEY.ITM);
        CEP.TRC(SCCGWA, AIRTMIB.KEY.SEQ);
        CEP.TRC(SCCGWA, AIRTMIB.KEY.SEQ);
        CEP.TRC(SCCGWA, WS_FIRST_FLAG);
        CEP.TRC(SCCGWA, WS_PREV_OPER_FLAG);
        CEP.TRC(SCCGWA, WS_PREV_RESULT_FLAG);
        if (WS_FIRST_FLAG == 'N' 
            && WS_PREV_OPER_FLAG == 'Y') {
            if (AIRCMIB.KEY.GL_BOOK.equalsIgnoreCase(AIRTMIB.KEY.GL_BOOK) 
                && AIRCMIB.KEY.BR == AIRTMIB.KEY.BR 
                && AIRCMIB.KEY.ITM.equalsIgnoreCase(AIRTMIB.KEY.ITM) 
                && AIRCMIB.KEY.SEQ == AIRTMIB.KEY.SEQ) {
                if (WS_PREV_RESULT_FLAG == 'F') {
                    AICRCMIB.RETURN_INFO = 'F';
                    IBS.CLONE(SCCGWA, AIRTMIB, AIRCMIB);
                } else if (WS_PREV_RESULT_FLAG == 'N') {
                    AICRCMIB.RETURN_INFO = 'N';
                } else if (WS_PREV_RESULT_FLAG == 'O') {
                    IBS.init(SCCGWA, SCCEXCP);
                    SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITCMIB;
                    SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                    SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
                    B_DB_EXCP();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    T000_READ_AITCMIB();
                    if (pgmRtn) return;
                }
            } else {
                T000_READ_AITCMIB();
                if (pgmRtn) return;
            }
        } else {
            T000_READ_AITCMIB();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITCMIB_RD = new DBParm();
        AITCMIB_RD.TableName = "AITCMIB";
        IBS.DELETE(SCCGWA, AIRCMIB, AITCMIB_RD);
    }
    public void B070_READFIR_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "-------READ RECORD FIRST----------");
        CEP.TRC(SCCGWA, AIRCMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.ITM);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
        AITCMIB_RD = new DBParm();
        AITCMIB_RD.TableName = "AITCMIB";
        AITCMIB_RD.where = "GL_BOOK = :AIRCMIB.KEY.GL_BOOK "
            + "AND ITM = :AIRCMIB.KEY.ITM "
            + "AND SEQ = :AIRCMIB.KEY.SEQ";
        AITCMIB_RD.fst = true;
        IBS.READ(SCCGWA, AIRCMIB, this, AITCMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITCMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (AICRCMIB.OPT == 'I') {
            T000_STARTBR_ITM();
            if (pgmRtn) return;
        } else if (AICRCMIB.OPT == 'R') {
            T000_STARTBR_ITM_BR();
            if (pgmRtn) return;
        } else if (AICRCMIB.OPT == 'C') {
            T000_STARTBR_CNM();
            if (pgmRtn) return;
        } else if (AICRCMIB.OPT == 'O') {
            T000_STARTBR_ITM_CNM();
            if (pgmRtn) return;
        } else if (AICRCMIB.OPT == 'A') {
            T000_STARTBR_ALL_BR();
            if (pgmRtn) return;
        } else if (AICRCMIB.OPT == 'N') {
            T000_READNEXT_AITCMIB();
            if (pgmRtn) return;
        } else if (AICRCMIB.OPT == 'E') {
            T000_ENDBR_AITCMIB();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRCMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITCMIB() throws IOException,SQLException,Exception {
        AITCMIB_RD = new DBParm();
        AITCMIB_RD.TableName = "AITCMIB";
        IBS.READ(SCCGWA, AIRCMIB, AITCMIB_RD);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.ITM);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, AIRCMIB.STS);
            AICRCMIB.RETURN_INFO = 'F';
            WS_PREV_RESULT_FLAG = 'F';
            IBS.CLONE(SCCGWA, AIRCMIB, AIRTMIB);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCMIB.RETURN_INFO = 'N';
            WS_PREV_RESULT_FLAG = 'N';
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRCMIB.KEY);
