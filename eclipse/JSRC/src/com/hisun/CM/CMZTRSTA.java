package com.hisun.CM;

import com.hisun.BP.BPRCLIB;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZTRSTA {
    DBParm CMTTRSTA_RD;
    boolean pgmRtn = false;
    char WS_CMTTRSTA_UPDATE_FLG = ' ';
    char WS_CMTTRSTA_FOUND_FLG = ' ';
    CMRTRSTA CMRTRSTI = new CMRTRSTA();
    CMRTRSTA CMRTRSTA = new CMRTRSTA();
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    CMCTLIBB CMCTLIBB;
    CMRTRSTA CMRLRSTA;
    public void MP(SCCGWA SCCGWA, CMCTLIBB CMCTLIBB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCTLIBB = CMCTLIBB;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZTRSTA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCTLIBB.RC);
        IBS.init(SCCGWA, CMRTRSTI);
        IBS.init(SCCGWA, CMRTRSTA);
        CMCTLIBB.RETURN_INFO = 'F';
        CMRLRSTA = (BPRCLIB) CMCTLIBB.POINTER;
        IBS.CLONE(SCCGWA, CMRLRSTA, CMRTRSTI);
        CEP.TRC(SCCGWA, CMRTRSTI);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK();
        if (pgmRtn) return;
        if (CMCTLIBB.FUNC == '0') {
            B020_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (CMCTLIBB.FUNC == '1') {
            B030_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (CMCTLIBB.FUNC == '2') {
            B040_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (CMCTLIBB.FUNC == '3') {
            B050_FUNC_READ();
            if (pgmRtn) return;
        } else if (CMCTLIBB.FUNC == '4') {
            B060_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_FUNC_ERROR, CMCTLIBB.RC);
            CMCTLIBB.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CMCTLIBB.RETURN_INFO = 'F';
            CEP.TRC(SCCGWA, "======DB NORMAL...");
        } else {
            CMCTLIBB.RETURN_INFO = 'E';
            CEP.TRC(SCCGWA, "======DB ERROR...");
        }
        CEP.TRC(SCCGWA, CMRTRSTA);
        CEP.TRC(SCCGWA, "================ END OF MAIN ================ ");
    }
    public void B010_CHECK() throws IOException,SQLException,Exception {
    }
    public void B020_FUNC_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRTRSTA);
        CMRTRSTA.KEY.FILE_NAME = CMRTRSTI.KEY.FILE_NAME;
        T000_READUP_CMTTRSTA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMRTRSTI.SYS_DATE);
        CEP.TRC(SCCGWA, CMRTRSTA.SYS_DATE);
        if (WS_CMTTRSTA_FOUND_FLG == 'Y') {
            if (CMRTRSTI.SYS_DATE == CMRTRSTA.SYS_DATE) {
                IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_CMTRSTA_ALREADY_EXST, CMCTLIBB.RC);
                CMCTLIBB.RETURN_INFO = 'E';
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CLONE(SCCGWA, CMRTRSTI, CMRTRSTA);
                T000_REWRITE_CMTTRSTA();
                if (pgmRtn) return;
            }
        } else {
            IBS.CLONE(SCCGWA, CMRTRSTI, CMRTRSTA);
            T000_WRITE_CMTTRSTA();
            if (pgmRtn) return;
        }
    }
    public void B030_FUNC_DELETE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRTRSTA);
        CMRTRSTA.KEY.FILE_NAME = CMRTRSTI.KEY.FILE_NAME;
        T000_READUP_CMTTRSTA();
        if (pgmRtn) return;
        if (WS_CMTTRSTA_FOUND_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_CMTRSTA_NOT_FOUND, CMCTLIBB.RC);
            CMCTLIBB.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        T000_DELETE_CMTTRSTA();
        if (pgmRtn) return;
    }
    public void B040_FUNC_REWRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRTRSTA);
        IBS.CLONE(SCCGWA, CMRTRSTI, CMRTRSTA);
        T000_REWRITE_CMTTRSTA();
        if (pgmRtn) return;
    }
    public void B050_FUNC_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRTRSTA);
        CMRTRSTA.KEY.FILE_NAME = CMRTRSTI.KEY.FILE_NAME;
        T000_READ_CMTTRSTA();
        if (pgmRtn) return;
        if (WS_CMTTRSTA_FOUND_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_CMTRSTA_NOT_FOUND, CMCTLIBB.RC);
            CMCTLIBB.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CMRTRSTA, CMRLRSTA);
    }
    public void B060_FUNC_READ_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRTRSTA);
        CMRTRSTA.KEY.FILE_NAME = CMRTRSTI.KEY.FILE_NAME;
        T000_READUP_CMTTRSTA();
        if (pgmRtn) return;
        if (WS_CMTTRSTA_FOUND_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_CMTRSTA_NOT_FOUND, CMCTLIBB.RC);
            CMCTLIBB.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CMRTRSTA, CMRLRSTA);
    }
    public void T000_READ_CMTTRSTA() throws IOException,SQLException,Exception {
        CMTTRSTA_RD = new DBParm();
        CMTTRSTA_RD.TableName = "CMTTRSTA";
        IBS.READ(SCCGWA, CMRTRSTA, CMTTRSTA_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMTTRSTA_FOUND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CMTTRSTA_FOUND_FLG = 'N';
        } else {
            WS_CMTTRSTA_FOUND_FLG = 'E';
        }
    }
    public void T000_READUP_CMTTRSTA() throws IOException,SQLException,Exception {
        CMTTRSTA_RD = new DBParm();
        CMTTRSTA_RD.TableName = "CMTTRSTA";
        CMTTRSTA_RD.upd = true;
        IBS.READ(SCCGWA, CMRTRSTA, CMTTRSTA_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMTTRSTA_FOUND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CMTTRSTA_FOUND_FLG = 'N';
        } else {
            WS_CMTTRSTA_FOUND_FLG = 'E';
        }
    }
    public void T000_WRITE_CMTTRSTA() throws IOException,SQLException,Exception {
        CMTTRSTA_RD = new DBParm();
        CMTTRSTA_RD.TableName = "CMTTRSTA";
        IBS.WRITE(SCCGWA, CMRTRSTA, CMTTRSTA_RD);
    }
    public void T000_DELETE_CMTTRSTA() throws IOException,SQLException,Exception {
        CMTTRSTA_RD = new DBParm();
        CMTTRSTA_RD.TableName = "CMTTRSTA";
        IBS.DELETE(SCCGWA, CMRTRSTA, CMTTRSTA_RD);
    }
    public void T000_REWRITE_CMTTRSTA() throws IOException,SQLException,Exception {
        CMTTRSTA_RD = new DBParm();
        CMTTRSTA_RD.TableName = "CMTTRSTA";
        IBS.REWRITE(SCCGWA, CMRTRSTA, CMTTRSTA_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CMCTLIBB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CMCTLIBB=");
            CEP.TRC(SCCGWA, CMCTLIBB);
        }
    } //FROM #ENDIF
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
