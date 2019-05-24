package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUACNO {
    int JIBS_tmp_int;
    DBParm BPTACOBL_RD;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRACOBL BPRACOBL = new BPRACOBL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCUACNO BPCUACNO;
    public void MP(SCCGWA SCCGWA, BPCUACNO BPCUACNO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUACNO = BPCUACNO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZUACNO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUACNO.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (BPCUACNO.FUNC == 'R') {
            B070_READ_OBL_AC();
            R000_TRANS_DATA_OUTPUT();
        } else if (BPCUACNO.FUNC == 'U') {
            B090_USE_OBL_AC();
            R000_TRANS_DATA_OUTPUT();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCUACNO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCUACNO.FUNC == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_FUNC, BPCUACNO.RC);
        }
        if (BPCUACNO.DATA.AC_NO.trim().length() == 0 
            && (BPCUACNO.DATA.AC_FLG == ' ' 
            || BPCUACNO.DATA.AC_TYPE == ' ' 
            || BPCUACNO.DATA.AC_NO_SEQ == 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.E_OBLAC_KEY_MUST, BPCUACNO.RC);
        }
    }
    public void B070_READ_OBL_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRACOBL);
        if (BPCUACNO.DATA.AC_FLG != ' ' 
            && BPCUACNO.DATA.AC_TYPE != ' ' 
            && BPCUACNO.DATA.AC_NO_SEQ != 0) {
            BPRACOBL.KEY.AC_FLG = BPCUACNO.DATA.AC_FLG;
            BPRACOBL.KEY.AC_TYPE = BPCUACNO.DATA.AC_TYPE;
            BPRACOBL.KEY.AC_NO_SEQ = BPCUACNO.DATA.AC_NO_SEQ;
        } else {
            if (BPCUACNO.DATA.AC_NO == null) BPCUACNO.DATA.AC_NO = "";
            JIBS_tmp_int = BPCUACNO.DATA.AC_NO.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) BPCUACNO.DATA.AC_NO += " ";
            BPRACOBL.KEY.AC_FLG = BPCUACNO.DATA.AC_NO.substring(0, 1).charAt(0);
            if (BPCUACNO.DATA.AC_NO == null) BPCUACNO.DATA.AC_NO = "";
            JIBS_tmp_int = BPCUACNO.DATA.AC_NO.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) BPCUACNO.DATA.AC_NO += " ";
            BPRACOBL.KEY.AC_TYPE = BPCUACNO.DATA.AC_NO.substring(2 - 1, 2 + 1 - 1).charAt(0);
            if (BPCUACNO.DATA.AC_NO == null) BPCUACNO.DATA.AC_NO = "";
            JIBS_tmp_int = BPCUACNO.DATA.AC_NO.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) BPCUACNO.DATA.AC_NO += " ";
            if (BPCUACNO.DATA.AC_NO.substring(5 - 1, 5 + 10 - 1).trim().length() == 0) BPRACOBL.KEY.AC_NO_SEQ = 0;
            else BPRACOBL.KEY.AC_NO_SEQ = Long.parseLong(BPCUACNO.DATA.AC_NO.substring(5 - 1, 5 + 10 - 1));
        }
        T000_READ_BPTACOBL();
    }
    public void B090_USE_OBL_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRACOBL);
        if (BPCUACNO.DATA.AC_TYPE != ' ' 
            && BPCUACNO.DATA.AC_NO_SEQ != 0) {
            BPRACOBL.KEY.AC_FLG = BPCUACNO.DATA.AC_FLG;
            BPRACOBL.KEY.AC_TYPE = BPCUACNO.DATA.AC_TYPE;
            BPRACOBL.KEY.AC_NO_SEQ = BPCUACNO.DATA.AC_NO_SEQ;
        } else {
            if (BPCUACNO.DATA.AC_NO == null) BPCUACNO.DATA.AC_NO = "";
            JIBS_tmp_int = BPCUACNO.DATA.AC_NO.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) BPCUACNO.DATA.AC_NO += " ";
            if (BPCUACNO.DATA.AC_NO.substring(7 - 1, 7 + 9 - 1).trim().length() == 0) BPRACOBL.KEY.AC_NO_SEQ = 0;
            else BPRACOBL.KEY.AC_NO_SEQ = Long.parseLong(BPCUACNO.DATA.AC_NO.substring(7 - 1, 7 + 9 - 1));
        }
        T000_READUPDATE_BPTACOBL();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPRACOBL.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRACOBL.LAST_SUP = SCCGMSG.SUP1_ID;
            BPRACOBL.USED_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRACOBL.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRACOBL.USED_FLG = 'Y';
            T000_UPDATE_BPTACOBL();
        }
    }
    public void T000_READ_BPTACOBL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_FLG);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_TYPE);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_NO_SEQ);
        BPTACOBL_RD = new DBParm();
        BPTACOBL_RD.TableName = "BPTACOBL";
        IBS.READ(SCCGWA, BPRACOBL, BPTACOBL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCUACNO.RC);
        }
    }
    public void T000_UPDATE_BPTACOBL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_FLG);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_TYPE);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_NO_SEQ);
        BPTACOBL_RD = new DBParm();
        BPTACOBL_RD.TableName = "BPTACOBL";
        IBS.REWRITE(SCCGWA, BPRACOBL, BPTACOBL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY, BPCUACNO.RC);
        }
    }
    public void T000_READUPDATE_BPTACOBL() throws IOException,SQLException,Exception {
        BPTACOBL_RD = new DBParm();
        BPTACOBL_RD.TableName = "BPTACOBL";
        BPTACOBL_RD.eqWhere = "AC_NO_SEQ";
        BPTACOBL_RD.upd = true;
        IBS.READ(SCCGWA, BPRACOBL, BPTACOBL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCUACNO.RC);
        } else {
        }
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCUACNO.DATA.AC_FLG = BPRACOBL.KEY.AC_FLG;
        BPCUACNO.DATA.AC_TYPE = BPRACOBL.KEY.AC_TYPE;
        BPCUACNO.DATA.AC_NO = BPRACOBL.AC_NO;
        BPCUACNO.DATA.CI_NO = BPRACOBL.CI_NO;
        BPCUACNO.DATA.AC_NO_SEQ = BPRACOBL.KEY.AC_NO_SEQ;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUACNO.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUACNO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUACNO = ");
            CEP.TRC(SCCGWA, BPCUACNO);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
