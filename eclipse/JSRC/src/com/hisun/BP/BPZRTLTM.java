package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTLTM {
    DBParm BPTTLT_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTLTM";
    String K_TBL_BANK = "BPTBANK ";
    String K_TBL_TLT = "BPTTLT  ";
    int WS_COUNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTLT BPRTLT = new BPRTLT();
    SCCGWA SCCGWA;
    BPCRTLTM BPCRTLTM;
    BPRTLT BPRTLR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTST;
    public void MP(SCCGWA SCCGWA, BPCRTLTM BPCRTLTM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTLTM = BPCRTLTM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTLTM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTST = (com.hisun.BP.BPRTLT) BPCRTLTM.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTLR = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        IBS.init(SCCGWA, BPRTLT);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRTLTM.RC);
        CEP.TRC(SCCGWA, BPCRTLTM.INFO.LEN);
        IBS.CLONE(SCCGWA, BPRTST, BPRTLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRTLTM.INFO.FUNC);
        CEP.TRC(SCCGWA, BPRTLT);
        if (BPCRTLTM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTM.INFO.FUNC == 'M') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTM.INFO.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTM.INFO.FUNC == 'Q') {
            CEP.TRC(SCCGWA, BPCRTLTM.INFO.FUNC);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTLTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "AAAA       ");
        CEP.TRC(SCCGWA, BPCRTLTM.RC);
        IBS.CLONE(SCCGWA, BPRTLT, BPRTST);
        CEP.TRC(SCCGWA, "BBBB       ");
        CEP.TRC(SCCGWA, BPCRTLTM.RC);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTLT();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTLT_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
        CEP.TRC(SCCGWA, BPRTLT.TLR_BR);
        T000_REWRITE_BPTTLT();
        if (pgmRtn) return;
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTLT();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_BPTTLT();
        if (pgmRtn) return;
    }
    public void T000_DELETE_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        IBS.DELETE(SCCGWA, BPRTLT, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLTM.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCRTLTM.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTLT, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTLTM.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY, BPCRTLTM.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTLT_UPD() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.upd = true;
        BPTTLT_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLT, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLTM.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCRTLTM.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPRTLT.TM_OUT_LMT);
        CEP.TRC(SCCGWA, BPRTLT.ACC_SEN_CUS);
        CEP.TRC(SCCGWA, BPRTLT.ACC_SEN_GL);
        CEP.TRC(SCCGWA, BPRTLT.SUPER_FLG);
        CEP.TRC(SCCGWA, BPRTLT.PRT_NAME1);
        CEP.TRC(SCCGWA, BPRTLT.PRT_NAME2);
        CEP.TRC(SCCGWA, BPRTLT.PRT_NAME3);
    }
    public void T000_REWRITE_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, BPRTLT, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLTM.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCRTLTM.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_QUERY_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLT, BPTTLT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPCRTLTM.RC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLTM.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCRTLTM.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCRTLTM.RC);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTLTM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTLTM = ");
            CEP.TRC(SCCGWA, BPCRTLTM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
