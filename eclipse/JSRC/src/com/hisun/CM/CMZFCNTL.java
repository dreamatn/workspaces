package com.hisun.CM;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZFCNTL {
    brParm CMTFCNTL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    DBParm CMTFCNTL_RD;
    boolean pgmRtn = false;
    String CPN_P_INQ_PARM = "BP-PARM-READ        ";
    char CMZFCNTL_FILLER1 = ' ';
    CMZFCNTL_WS_MSGID WS_MSGID = new CMZFCNTL_WS_MSGID();
    String WS_INFO = " ";
    char WS_SCENE_FLG = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    String WS_BU_TYPE = " ";
    String WS_SYSID = " ";
    CMRFCNTL CMRFCNTL = new CMRFCNTL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    CMCFCNTL CMCFCNTL;
    CMRFCNTT CMRFCNTT;
    public void MP(SCCGWA SCCGWA, CMCFCNTL CMCFCNTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCFCNTL = CMCFCNTL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZFCNTL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRFCNTL);
        CMCFCNTL.RC.RC_APP = "CM";
        CMCFCNTL.RC.RC_RTNCODE = 0;
        CMRFCNTT = (CMRFCNTT) CMCFCNTL.DAT_PTR;
        BPCPRMR.DAT_PTR = BPRPRMT;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMRFCNTT.BU_TYPE);
        CEP.TRC(SCCGWA, CMCFCNTL.FUNC);
        B01_CHECK();
        if (pgmRtn) return;
        if (CMCFCNTL.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (CMCFCNTL.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (CMCFCNTL.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (CMCFCNTL.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (CMCFCNTL.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else if (CMCFCNTL.FUNC == '5') {
            B07_INQUIRE_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + CMCFCNTL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (CMCFCNTL.FUNC2 == '1' 
            || CMCFCNTL.FUNC == '3' 
            || CMCFCNTL.FUNC == '4') {
            CEP.TRC(SCCGWA, "BACK VALUE!");
            B08_RETURN_VALUE();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
    }
    public void B07_INQUIRE_UPDATE() throws IOException,SQLException,Exception {
        if (CMCFCNTL.FUNC2 == '0') {
            T001_STARTBR_PROCESS();
            if (pgmRtn) return;
        } else if (CMCFCNTL.FUNC2 == '3') {
            T010_STARTBR_TYPE_PROCESS();
            if (pgmRtn) return;
        } else if (CMCFCNTL.FUNC2 == '4') {
            T020_STARTBR_SYSID_PROCESS();
            if (pgmRtn) return;
        } else if (CMCFCNTL.FUNC2 == '1') {
            T002_READNEXT_PROCESS();
            if (pgmRtn) return;
        } else if (CMCFCNTL.FUNC2 == '2') {
            T003_ENDBR_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + CMCFCNTL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B08_RETURN_VALUE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMRFCNTL.KEY.BU_TYPE);
        CEP.TRC(SCCGWA, CMRFCNTL.KEY.SYS_ID);
        CEP.TRC(SCCGWA, CMRFCNTL.TASK_NO);
        CEP.TRC(SCCGWA, CMRFCNTL.FILE_IO);
        CEP.TRC(SCCGWA, CMRFCNTL.MAX_NO);
        CEP.TRC(SCCGWA, CMRFCNTL.USE_NO);
        CMRFCNTT.BU_TYPE = CMRFCNTL.KEY.BU_TYPE;
        CMRFCNTT.SYS_ID = CMRFCNTL.KEY.SYS_ID;
        CMRFCNTT.SCENE_FLG = CMRFCNTL.SCENE_FLG;
        CMRFCNTT.FILE_IO = CMRFCNTL.FILE_IO;
        CMRFCNTT.TASK_NO = CMRFCNTL.TASK_NO;
        CMRFCNTT.MAX_NO = CMRFCNTL.MAX_NO;
        CMRFCNTT.USE_NO = CMRFCNTL.USE_NO;
    }
    public void T001_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        CMTFCNTL_BR.rp = new DBParm();
        CMTFCNTL_BR.rp.TableName = "CMTFCNTL";
        CMTFCNTL_BR.rp.order = "BU_TYPE";
        IBS.STARTBR(SCCGWA, CMRFCNTL, CMTFCNTL_BR);
    }
    public void T010_STARTBR_TYPE_PROCESS() throws IOException,SQLException,Exception {
        WS_BU_TYPE = CMRFCNTT.BU_TYPE;
        CMTFCNTL_BR.rp = new DBParm();
        CMTFCNTL_BR.rp.TableName = "CMTFCNTL";
        CMTFCNTL_BR.rp.where = "BU_TYPE = :WS_BU_TYPE";
        CMTFCNTL_BR.rp.order = "BU_TYPE";
        IBS.STARTBR(SCCGWA, CMRFCNTL, this, CMTFCNTL_BR);
    }
    public void T020_STARTBR_SYSID_PROCESS() throws IOException,SQLException,Exception {
        WS_SYSID = CMRFCNTT.SYS_ID;
        CMTFCNTL_BR.rp = new DBParm();
        CMTFCNTL_BR.rp.TableName = "CMTFCNTL";
        CMTFCNTL_BR.rp.where = "SYS_ID = :WS_SYSID";
        CMTFCNTL_BR.rp.order = "BU_TYPE";
        IBS.STARTBR(SCCGWA, CMRFCNTL, this, CMTFCNTL_BR);
    }
    public void T002_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CMRFCNTL, this, CMTFCNTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CMRFCNTT.END_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + CMCFCNTL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, CMRFCNTT.END_FLG);
    }
    public void T003_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CMTFCNTL_BR);
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        R10_READ_BPTPARM();
        if (pgmRtn) return;
        R11_COMPARE_SCENE_FLG();
        if (pgmRtn) return;
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST, CMCFCNTL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CMRFCNTL);
        CMRFCNTL.KEY.BU_TYPE = CMRFCNTT.BU_TYPE;
        CMRFCNTL.KEY.SYS_ID = CMRFCNTT.SYS_ID;
        CMRFCNTL.SCENE_FLG = CMRFCNTT.SCENE_FLG;
        CMRFCNTL.FILE_IO = CMRFCNTT.FILE_IO;
        CMRFCNTL.TASK_NO = CMRFCNTT.TASK_NO;
        CMRFCNTL.MAX_NO = CMRFCNTT.MAX_NO;
        CMRFCNTL.USE_NO = CMRFCNTT.USE_NO;
        CEP.TRC(SCCGWA, CMRFCNTL.KEY.BU_TYPE);
        CEP.TRC(SCCGWA, CMRFCNTL.KEY.SYS_ID);
        CEP.TRC(SCCGWA, CMRFCNTL.SCENE_FLG);
        CEP.TRC(SCCGWA, CMRFCNTL.FILE_IO);
        CEP.TRC(SCCGWA, CMRFCNTL.TASK_NO);
        CEP.TRC(SCCGWA, CMRFCNTL.MAX_NO);
        CEP.TRC(SCCGWA, CMRFCNTL.USE_NO);
        T00_WRITE_CMTFCNTL();
        if (pgmRtn) return;
    }
    public void R11_COMPARE_SCENE_FLG() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        WS_SCENE_FLG = JIBS_tmp_str[0].charAt(0);
        CEP.TRC(SCCGWA, WS_SCENE_FLG);
        CEP.TRC(SCCGWA, CMRFCNTT.SCENE_FLG);
        if (CMRFCNTT.SCENE_FLG != WS_SCENE_FLG) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, WS_MSGID);
            WS_INFO = "SCENE FLG MUST BE:" + WS_SCENE_FLG + ".WHICH IS THE SAME AS THAT IN BPTPARM!";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_INFO);
        }
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        CMRFCNTL.KEY.BU_TYPE = CMRFCNTT.BU_TYPE;
        CMRFCNTL.KEY.SYS_ID = CMRFCNTT.SYS_ID;
        T00_READUPDATE_CMTFCNTL();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            T00_DELETE_CMTFCNTL();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, CMCFCNTL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRFCNTL);
        R10_READ_BPTPARM();
        if (pgmRtn) return;
        R11_COMPARE_SCENE_FLG();
        if (pgmRtn) return;
        CMRFCNTL.KEY.BU_TYPE = CMRFCNTT.BU_TYPE;
        CMRFCNTL.KEY.SYS_ID = CMRFCNTT.SYS_ID;
        CMRFCNTL.SCENE_FLG = CMRFCNTT.SCENE_FLG;
        CMRFCNTL.FILE_IO = CMRFCNTT.FILE_IO;
        CMRFCNTL.TASK_NO = CMRFCNTT.TASK_NO;
        CMRFCNTL.MAX_NO = CMRFCNTT.MAX_NO;
        CMRFCNTL.USE_NO = CMRFCNTT.USE_NO;
        CEP.TRC(SCCGWA, CMRFCNTL.KEY.BU_TYPE);
        CEP.TRC(SCCGWA, CMRFCNTL.KEY.SYS_ID);
        CEP.TRC(SCCGWA, CMRFCNTL.SCENE_FLG);
        CEP.TRC(SCCGWA, CMRFCNTL.FILE_IO);
        CEP.TRC(SCCGWA, CMRFCNTL.TASK_NO);
        CEP.TRC(SCCGWA, CMRFCNTL.MAX_NO);
        CEP.TRC(SCCGWA, CMRFCNTL.USE_NO);
        T00_REWRITE_CMTFCNTL();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, CMCFCNTL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CMRFCNTT.BU_TYPE = CMRFCNTL.KEY.BU_TYPE;
        CMRFCNTT.SYS_ID = CMRFCNTL.KEY.SYS_ID;
        CMRFCNTT.SCENE_FLG = CMRFCNTL.SCENE_FLG;
        CMRFCNTT.FILE_IO = CMRFCNTL.FILE_IO;
        CMRFCNTT.TASK_NO = CMRFCNTL.TASK_NO;
        CMRFCNTT.MAX_NO = CMRFCNTL.MAX_NO;
        CMRFCNTT.USE_NO = CMRFCNTL.USE_NO;
    }
    public void B06_FUNC_READ_UPDATE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = 'Y';
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, CMCFCNTL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CMRFCNTT.BU_TYPE = CMRFCNTL.KEY.BU_TYPE;
        CMRFCNTT.SYS_ID = CMRFCNTL.KEY.SYS_ID;
        CMRFCNTT.SCENE_FLG = CMRFCNTL.SCENE_FLG;
        CMRFCNTT.FILE_IO = CMRFCNTL.FILE_IO;
        CMRFCNTT.TASK_NO = CMRFCNTL.TASK_NO;
        CMRFCNTT.MAX_NO = CMRFCNTL.MAX_NO;
        CMRFCNTT.USE_NO = CMRFCNTL.USE_NO;
    }
    public void R00_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRFCNTL);
        CMRFCNTL.KEY.BU_TYPE = CMRFCNTT.BU_TYPE;
        CMRFCNTL.KEY.SYS_ID = CMRFCNTT.SYS_ID;
        CEP.TRC(SCCGWA, CMRFCNTL.KEY.BU_TYPE);
        CEP.TRC(SCCGWA, CMRFCNTL.KEY.SYS_ID);
        T00_READ_CMTFCNTL();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y' 
            && WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_CMTFCNTL();
            if (pgmRtn) return;
        }
    }
    public void R10_READ_BPTPARM() throws IOException,SQLException,Exception {
        BPCPRMR.FUNC = ' ';
        BPCPRMR.TYP = "FITYP";
        BPCPRMR.CD = CMRFCNTT.BU_TYPE;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PARM, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_MSGID.WS_MSG_AP = BPCPRMR.RC.RC_APP;
            WS_MSGID.WS_MSG_CODE = BPCPRMR.RC.RC_RTNCODE;
            WS_INFO = "READ BPTPARM ERROR!";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_INFO);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_MSGID.WS_MSG_AP = BPCPRMR.RC.RC_APP;
            WS_MSGID.WS_MSG_CODE = BPCPRMR.RC.RC_RTNCODE;
            WS_INFO = "BUSINESS TYPE: " + CMRFCNTT.BU_TYPE + " DOES NO IN " + "BPTPARM TABLE!";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_INFO);
        }
    }
    public void T00_READ_CMTFCNTL() throws IOException,SQLException,Exception {
        CMTFCNTL_RD = new DBParm();
        CMTFCNTL_RD.TableName = "CMTFCNTL";
        CMTFCNTL_RD.where = "BU_TYPE = :CMRFCNTL.KEY.BU_TYPE "
            + "AND SYS_ID = :CMRFCNTL.KEY.SYS_ID";
        IBS.READ(SCCGWA, CMRFCNTL, this, CMTFCNTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_WRITE_CMTFCNTL() throws IOException,SQLException,Exception {
        CMTFCNTL_RD = new DBParm();
        CMTFCNTL_RD.TableName = "CMTFCNTL";
        IBS.WRITE(SCCGWA, CMRFCNTL, CMTFCNTL_RD);
    }
    public void T00_DELETE_CMTFCNTL() throws IOException,SQLException,Exception {
        CMTFCNTL_RD = new DBParm();
        CMTFCNTL_RD.TableName = "CMTFCNTL";
        IBS.DELETE(SCCGWA, CMRFCNTL, CMTFCNTL_RD);
    }
    public void T00_READUPDATE_CMTFCNTL() throws IOException,SQLException,Exception {
        CMTFCNTL_RD = new DBParm();
        CMTFCNTL_RD.TableName = "CMTFCNTL";
        CMTFCNTL_RD.upd = true;
        IBS.READ(SCCGWA, CMRFCNTL, CMTFCNTL_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_FOUND_FLG);
    }
    public void T00_REWRITE_CMTFCNTL() throws IOException,SQLException,Exception {
        CMTFCNTL_RD = new DBParm();
        CMTFCNTL_RD.TableName = "CMTFCNTL";
        IBS.REWRITE(SCCGWA, CMRFCNTL, CMTFCNTL_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CMCFCNTL.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "CMCFCNTL=");
            CEP.TRC(SCCGWA, CMCFCNTL);
        }
    } //FROM #ENDIF
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
