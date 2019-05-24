package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTMVBB {
    brParm BPTCMOV_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTMVBB";
    String K_TBL_CMOV = "BPTCMOV ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_CMOV_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCMOV BPRCMOV = new BPRCMOV();
    String WS_TMVBB_TLR = " ";
    char WS_TMVBB_MOV_TYP = ' ';
    String WS_TMVBB_CCY = " ";
    char WS_TMVBB_MOV_STS = ' ';
    SCCGWA SCCGWA;
    BPCTMVBB BPCTMVBB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCTMVBB BPCTMVBB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTMVBB = BPCTMVBB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTMVBB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRCMOV);
        IBS.init(SCCGWA, BPCTMVBB.RC);
        WS_TMVBB_MOV_TYP = BPCTMVBB.MOV_TYP;
        WS_TMVBB_CCY = BPCTMVBB.CCY;
        WS_TMVBB_MOV_STS = BPCTMVBB.MOV_STS;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTMVBB.FUNC == 'S') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCTMVBB.FUNC == 'A') {
            B010_STARTBRALL_PROC();
            if (pgmRtn) return;
        } else if (BPCTMVBB.FUNC == 'T') {
            B010_STARTBR_ALL_TLR_PROC();
            if (pgmRtn) return;
        } else if (BPCTMVBB.FUNC == 'R') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCTMVBB.FUNC == 'E') {
            B030_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTMVBB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        WS_TMVBB_TLR = BPCTMVBB.TLR;
        WS_TMVBB_MOV_TYP = BPCTMVBB.MOV_TYP;
        WS_TMVBB_MOV_STS = BPCTMVBB.MOV_STS;
        WS_TMVBB_CCY = BPCTMVBB.CCY;
        T000_STARTBR_TLR_STS_TYP_CCY();
        if (pgmRtn) return;
    }
    public void B010_STARTBRALL_PROC() throws IOException,SQLException,Exception {
        WS_TMVBB_TLR = BPCTMVBB.TLR;
        WS_TMVBB_MOV_TYP = BPCTMVBB.MOV_TYP;
        WS_TMVBB_MOV_STS = BPCTMVBB.MOV_STS;
        T000_STARTBRALL_TLR_STS_TYP();
        if (pgmRtn) return;
    }
    public void B010_STARTBR_ALL_TLR_PROC() throws IOException,SQLException,Exception {
        WS_TMVBB_TLR = BPCTMVBB.TLR;
        WS_TMVBB_MOV_STS = BPCTMVBB.MOV_STS;
        T000_STARTBR_ALL_TLR_STS();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTCMOV();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTCMOV();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_TLR_STS_TYP_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "IN_TLR = :WS_TMVBB_TLR "
            + "AND MOV_TYP = :WS_TMVBB_MOV_TYP "
            + "AND MOV_STS = :WS_TMVBB_MOV_STS "
            + "AND CCY = :WS_TMVBB_CCY";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBRALL_TLR_STS_TYP() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "IN_TLR = :WS_TMVBB_TLR "
            + "AND MOV_TYP = :WS_TMVBB_MOV_TYP "
            + "AND MOV_STS = :WS_TMVBB_MOV_STS";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_ALL_TLR_STS() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( IN_TLR = :WS_TMVBB_TLR "
            + "OR OUT_TLR = :WS_TMVBB_TLR ) "
            + "AND MOV_STS = :WS_TMVBB_MOV_STS";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_READNEXT_BPTCMOV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTMVBB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTMVBB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTCMOV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCMOV_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTMVBB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTMVBB = ");
            CEP.TRC(SCCGWA, BPCTMVBB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
