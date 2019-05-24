package com.hisun.PS;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQORG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PSZSTRZ {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_TEMP_RECORD = " ";
    String WS_EXG_AREA_NO = " ";
    char WS_TBL_FARM_FLAG = ' ';
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    PSCEXDEL PSCEXDEL = new PSCEXDEL();
    PSREINF PSREINF = new PSREINF();
    PSCB4220 PSCB4220 = new PSCB4220();
    PSROBLL PSROBLL = new PSROBLL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    PSRBSP01 PSRBSP01 = new PSRBSP01();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRWBSP SCCRWBSP;
    PSRBSP01 PSRBSP01A;
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZSTRZ return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        PSRBSP01A = (PSRBSP01) SCCRWBSP.INFO.PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, "111");
        IBS.init(SCCGWA, PSRBSP01);
        CEP.TRC(SCCGWA, "222");
        IBS.CLONE(SCCGWA, PSRBSP01A, PSRBSP01);
        SCCRWBSP.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        SCCRWBSP.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, "333");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCRWBSP.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + SCCRWBSP.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, PSRBSP01, PSRBSP01A);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSRBSP01);
        T000_WRITE_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP_UPD();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            S000_READUPDARE_PSTEINF();
            if (pgmRtn) return;
            S000_REWRITE_PSTEINF();
            if (pgmRtn) return;
        }
    }
    public void S000_READUPDARE_PSTEINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        PSTEINF_RD.where = "EXG_BK_NO = :PSREINF.KEY.EXG_BK_NO "
            + "AND EXG_TX_BR = :PSREINF.KEY.EXG_TX_BR";
        PSTEINF_RD.fst = true;
        PSTEINF_RD.upd = true;
        IBS.READ(SCCGWA, PSREINF, this, PSTEINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_DUKEY_REC_EXIST, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTOBLL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_REWRITE_PSTEINF() throws IOException,SQLException,Exception {
        PSREINF.EXG_SYS_STS = "04";
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.REWRITE(SCCGWA, PSREINF, PSTEINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BSP();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSRBSP01.KEY.AP_TYPE);
        CEP.TRC(SCCGWA, PSRBSP01.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, PSRBSP01.KEY.BAT_NO);
        PSTBSP01_RD = new DBParm();
        PSTBSP01_RD.TableName = "PSTBSP01";
        IBS.READ(SCCGWA, PSRBSP01, PSTBSP01_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
            CEP.TRC(SCCGWA, "AAA");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
            CEP.TRC(SCCGWA, "BBB");
        } else {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BSP() throws IOException,SQLException,Exception {
        PSTBSP01_RD = new DBParm();
        PSTBSP01_RD.TableName = "PSTBSP01";
        IBS.WRITE(SCCGWA, PSRBSP01, PSTBSP01_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            SCCRWBSP.RETURN_INFO = 'D';
        } else {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSRBSP01.KEY.AP_TYPE);
        CEP.TRC(SCCGWA, PSRBSP01.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, PSRBSP01.KEY.BAT_NO);
        PSTBSP01_RD = new DBParm();
        PSTBSP01_RD.TableName = "PSTBSP01";
        PSTBSP01_RD.upd = true;
        PSTBSP01_RD.col = "STATUS, RT_CODE, OUT_JRN, OUT_VCH_NO, OUT_DATA, TS";
        IBS.READ(SCCGWA, PSRBSP01, PSTBSP01_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
