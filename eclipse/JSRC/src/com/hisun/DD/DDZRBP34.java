package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZRBP34 {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTBSP34_RD;
    boolean pgmRtn = false;
    int WS_LENGTH = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    DDRBSP34 DDRBSP34 = new DDRBSP34();
    SCCGWA SCCGWA;
    SCCRWBSP SCCRWBSP;
    DDRBSP34 DDRBSP1;
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZRBP34 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDRBSP1 = (PSRBSP01) SCCRWBSP.INFO.PTR;
        CEP.TRC(SCCGWA, SCCRWBSP.INFO.PTR);
        IBS.init(SCCGWA, DDRBSP34);
        CEP.TRC(SCCGWA, SCCRWBSP.INFO.LEN);
        WS_LENGTH = 236;
        CEP.TRC(SCCGWA, WS_LENGTH);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDRBSP1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, DDRBSP1, DDRBSP34);
        CEP.TRC(SCCGWA, "DD");
        SCCRWBSP.RC.RC_MMO = "DD";
        CEP.TRC(SCCGWA, "ZERO");
        SCCRWBSP.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, "NORMAL");
        SCCRWBSP.RETURN_INFO = 'F';
        CEP.TRC(SCCGWA, "END-A000");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRBSP34.KEY.AP_TYPE);
        CEP.TRC(SCCGWA, DDRBSP34.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, DDRBSP34.KEY.BAT_NO);
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
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDRBSP34);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, DDRBSP34, DDRBSP1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BSP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BSP();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BSP();
        if (pgmRtn) return;
    }
    public void T000_READ_BSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRBSP34.KEY.AP_TYPE);
        CEP.TRC(SCCGWA, DDRBSP34.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, DDRBSP34.KEY.BAT_NO);
        DDTBSP34_RD = new DBParm();
        DDTBSP34_RD.TableName = "DDTBSP34";
        IBS.READ(SCCGWA, DDRBSP34, DDTBSP34_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BSP_REC_NOTFND, SCCRWBSP.RC);
            SCCRWBSP.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BSP() throws IOException,SQLException,Exception {
        DDTBSP34_RD = new DBParm();
        DDTBSP34_RD.TableName = "DDTBSP34";
        IBS.WRITE(SCCGWA, DDRBSP34, DDTBSP34_RD);
    }
    public void T000_READ_BSP_UPD() throws IOException,SQLException,Exception {
        DDTBSP34_RD = new DBParm();
        DDTBSP34_RD.TableName = "DDTBSP34";
        DDTBSP34_RD.col = "STATUS ,RT_CODE ,OUT_JRN ,OUT_VCH_NO";
        DDTBSP34_RD.upd = true;
        IBS.READ(SCCGWA, DDRBSP34, DDTBSP34_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BSP_REC_NOTFND, SCCRWBSP.RC);
            SCCRWBSP.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BSP() throws IOException,SQLException,Exception {
        DDTBSP34_RD = new DBParm();
        DDTBSP34_RD.TableName = "DDTBSP34";
        IBS.REWRITE(SCCGWA, DDRBSP34, DDTBSP34_RD);
    }
    public void T000_DELETE_BSP() throws IOException,SQLException,Exception {
        DDTBSP34_RD = new DBParm();
        DDTBSP34_RD.TableName = "DDTBSP34";
        IBS.DELETE(SCCGWA, DDRBSP34, DDTBSP34_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BSP_REC_NOTFND, SCCRWBSP.RC);
            SCCRWBSP.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (SCCRWBSP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCCRWBSP=");
            CEP.TRC(SCCGWA, SCCRWBSP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
