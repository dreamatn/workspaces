package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTSPCA {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTSPCA_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_BPZTSPCA = "BPZTSPCA";
    String TBL_BPTSPCA = "BPTSPCA ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRSPCA BPRSPCA = new BPRSPCA();
    SCCGWA SCCGWA;
    BPCRSPCA BPCRSPCA;
    BPRSPCA BPRSPCA1;
    public void MP(SCCGWA SCCGWA, BPCRSPCA BPCRSPCA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRSPCA = BPCRSPCA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTSPCA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRSPCA1 = (BPRSPCA) BPCRSPCA.INFO.POINTER;
        IBS.init(SCCGWA, BPRSPCA);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSPCA1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSPCA1, BPRSPCA);
        CEP.TRC(SCCGWA, BPRSPCA.KEY.CHK_TYP);
        CEP.TRC(SCCGWA, BPRSPCA.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRSPCA.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRSPCA.KEY.CCY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRSPCA.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSPCA.INFO.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSPCA.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRSPCA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSPCA);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSPCA, BPRSPCA1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRSPCA.KEY.BR == 0) {
            T000_STARTBR_BPTSPCA1();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTSPCA2();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTSPCA();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTSPCA();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTSPCA() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRSPCA, this, BPTSPCA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSPCA.INFO.RTN_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSPCA.INFO.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTSPCA1() throws IOException,SQLException,Exception {
        BPTSPCA_BR.rp = new DBParm();
        BPTSPCA_BR.rp.TableName = "BPTSPCA";
        BPTSPCA_BR.rp.where = "CHK_TYP LIKE :BPRSPCA.KEY.CHK_TYP "
            + "AND EXR_TYP LIKE :BPRSPCA.KEY.EXR_TYP "
            + "AND FWD_TENOR LIKE :BPRSPCA.KEY.FWD_TENOR "
            + "AND CCY LIKE :BPRSPCA.KEY.CCY "
            + "AND CORR_CCY LIKE :BPRSPCA.KEY.CORR_CCY";
        IBS.STARTBR(SCCGWA, BPRSPCA, this, BPTSPCA_BR);
    }
    public void T000_STARTBR_BPTSPCA2() throws IOException,SQLException,Exception {
        BPTSPCA_BR.rp = new DBParm();
        BPTSPCA_BR.rp.TableName = "BPTSPCA";
        BPTSPCA_BR.rp.where = "CHK_TYP LIKE :BPRSPCA.KEY.CHK_TYP "
            + "AND BR = :BPRSPCA.KEY.BR "
            + "AND CCY LIKE :BPRSPCA.KEY.CCY "
            + "AND CORR_CCY LIKE :BPRSPCA.KEY.CORR_CCY";
        IBS.STARTBR(SCCGWA, BPRSPCA, this, BPTSPCA_BR);
    }
    public void T000_ENDBR_BPTSPCA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTSPCA_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRSPCA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRSPCA = ");
            CEP.TRC(SCCGWA, BPCRSPCA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
